package org.transitclock.detours;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.transitclock.core.SpatialMatch;
import org.transitclock.db.structs.Location;
import org.transitclock.db.structs.VectorWithHeading;
import org.transitclock.detours.model.ApiDirection;
import org.transitclock.detours.model.ApiDiversion;
import org.transitclock.detours.model.ApiLocation;
import org.transitclock.detours.model.ApiRoute;
import org.transitclock.detours.model.ApiRouteDetails;
import org.transitclock.detours.model.ApiRoutes;
import org.transitclock.detours.model.ApiShape;
import org.transitclock.detours.model.ApiStop;
import org.transitclock.detours.model.DetourInformation;
import org.transitclock.detours.model.LatLng;
import org.transitclock.detours.model.Match;


/**
 * Controller for detours.
 * In order to put some logic, I had add spring support for web interface.
 * 2020-06-27
 * @author vperez
 *
 */
@Controller
public class DetourBuilder {

	
	private static final double CLOSENESS_THESHOLD = 15;//15 m
	@GetMapping(value = "/detourBuilder") 
	public ModelAndView firstView() 
	{ 
		ModelAndView mav = new ModelAndView("detourBuilder");  
		// must match the jsp page name which is being requested. 
		mav.addObject("greeting", "welcome"); 
		return mav; 
	} 	
	/**
	 * Saves a detour.
	 * It searches over the shape where the detour is.
	 * @param obj
	 * @return
	 */
	@RequestMapping(value="/detourBuilderSave",method = RequestMethod.POST,consumes =MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public String saveDetour(@RequestBody DetourInformation obj) {
		
		ApiRoutes routes = obj.getRoutes();
		ApiDirection  directionFound=null;
		for(ApiRouteDetails route:routes.getRoutes())
		{
			for( ApiDirection  direction:route.getDirection())
			{
				if(direction.getId().compareTo(obj.getSelectedDirection())==0)
				{
					directionFound=direction;
					break;
				}
			}
		}
		if(directionFound==null)
		{
			//TODO:
			 return "TODO: No direction found";
		}
		ApiShape shapeFound=null;
		//Take a look when there is more than one route!
		for(ApiShape shape:routes.getRoutes().get(0).getShape())
		{
			if(shape.getDirectionId().compareTo(obj.getSelectedDirection())==0)
			{
				shapeFound=shape;break;
			}
		}
		if(shapeFound==null)
		{
			//TODO:
			 return "TODO: No direction found";
		}
		List<ApiLocation> locations = shapeFound.getLocations();
		List<VectorWithHeading> vwh=new ArrayList<VectorWithHeading>();
		List<Match> mathcesFirstPoint=new ArrayList<Match>();
		List<Match> mathcesLastPoint=new ArrayList<Match>();
		LatLng[] latLogs = obj.getLatLngs();
		Location firstPoint = latLogs[0].toLocation();
		Location lastPoint = latLogs[latLogs.length-1].toLocation();
		List<ApiStop> stops=directionFound.getStop();
		
		double accumulatedDistance=0;
		ApiStop nextStop=null;
		ApiStop previousStop=null;
		double accumulatedStopDistace=0;
		int previousStopMathIndex=0;
		int nestStopMathIndex=0;
		//stops.get(0).get
		for(int i=1,j=0;i<locations.size();i++)
		{
		
			VectorWithHeading vector=new VectorWithHeading(locations.get(i-1).toLocation(), locations.get(i).toLocation());
			
			vwh.add(vector);
			accumulatedDistance+=vector.length();
			while(j<stops.size() && accumulatedStopDistace+stops.get(j).getPathLength()<accumulatedDistance)
			{
				//TODO: Set stop accumulated distance
				accumulatedStopDistace+=stops.get(j).getPathLength();
				if(j>1)
				{
					previousStop=stops.get(j);
				}
				if(j<stops.size())
				nextStop=stops.get(j);
				j++;
			}
			double distanceToSengmetFirstPoint=vector.distance(firstPoint);
			double distanceToSengmetLastPoint=vector.distance(lastPoint);
			if(distanceToSengmetFirstPoint<CLOSENESS_THESHOLD)
			{
				Match match= new Match();
				match.setMatchIndex(i);
				match.setDistanceAlongSegmnt(vector.matchDistanceAlongVector(firstPoint));
				match.setDistanceToSegment(distanceToSengmetFirstPoint);
				match.setAccumulatedDistance(accumulatedDistance);
				match.setNextStop(nextStop);
				match.setPreviousStop(previousStop);
				mathcesFirstPoint.add(match);
			}
			if(distanceToSengmetLastPoint<CLOSENESS_THESHOLD)
			{
				Match match= new Match();
				match.setMatchIndex(i);
				match.setDistanceAlongSegmnt(vector.matchDistanceAlongVector(lastPoint));
				match.setDistanceToSegment(distanceToSengmetLastPoint);
				match.setAccumulatedDistance(accumulatedDistance);
				match.setNextStop(nextStop);
				match.setPreviousStop(previousStop);
				mathcesLastPoint.add(match);
			}
	
		}
		/*
		 * If we have more than 1 possible match, lets verify if the segments are adjacents
		 * otherwise, we need to do something else
		 * TODO: What if we have more than one possible start.
		 * 	- One possibility: The paths should always be build in the correct direction, and to
		 *  check against the final point. 
		 */
		Match closestLastMatch=null;
		Match closestFisrtMatch=null;
		if(mathcesLastPoint.size()>1)
		{
			boolean adjacents=true;
			for(int i =0;i<mathcesLastPoint.size();i++)
			{
				if(i<mathcesLastPoint.size()-1)
					adjacents&=(mathcesLastPoint.get(i+1).getMatchIndex()-mathcesLastPoint.get(i).getMatchIndex()==1);
				if(closestLastMatch==null || closestLastMatch.getDistanceToSegment()>mathcesLastPoint.get(i).getDistanceToSegment())
					closestLastMatch=mathcesLastPoint.get(i);
					
			}
			if(!adjacents)
			{
				return "TODO: Handle not countigous segments at the end";
			}
		}
		if(mathcesFirstPoint.size()>1)
		{
			boolean adjacents=true;
			for(int i =0;i<mathcesFirstPoint.size();i++)
			{
				if(i<mathcesFirstPoint.size()-1)
					adjacents&=(mathcesLastPoint.get(i+1).getMatchIndex()-mathcesFirstPoint.get(i).getMatchIndex()==1);
				if(closestFisrtMatch==null || closestFisrtMatch.getDistanceToSegment()>mathcesFirstPoint.get(i).getDistanceToSegment())
					closestFisrtMatch=mathcesFirstPoint.get(i);
	
			}
			if(!adjacents)
			{
				return "TODO: Handle not countigous segments at the beggining";
			}
		}
		
		ApiDiversion diversion=new ApiDiversion();
		///diversion.setDistanceStartAlongSegment(closestFisrtMatch.getAccumulatedDistance()-closestFisrtMatch.getPreviousStop().get);
		System.out.println(" MATCHES FP "+mathcesFirstPoint );
		System.out.println(" MATCHES LP "+mathcesLastPoint );
		
		
		System.out.println("GOT "+obj);
	    return "Hello";
	}
}
