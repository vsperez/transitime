package org.transitime.gtfs;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.transitime.db.structs.Stop;
import org.transitime.db.structs.TripPattern;
import org.transitime.gtfs.gtfsStructs.GtfsShape;
import org.transitime.utils.IntervalTimer;

import com.bmwcarit.barefoot.road.BaseRoad;
import com.bmwcarit.barefoot.road.BfmapWriter;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Line;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;

/**
 * @author Sean Óg Crudden
 * Creates a barefoot map file from shapes.txt. Barefoot can then be used to match avl points to the shapes.
 *
 */
public class BareFootProcessor extends StopPathProcessor {
	private static final Logger logger = 
			LoggerFactory.getLogger(BareFootProcessor.class);

	public BareFootProcessor(Collection<GtfsShape> gtfsShapes, Map<String, Stop> stopsMap,
			Collection<TripPattern> tripPatterns, double offsetDistance, double maxStopToPathDistance,
			double maxDistanceForEliminatingVertices, boolean trimPathBeforeFirstStopOfTrip) {
		super(gtfsShapes, stopsMap, tripPatterns, offsetDistance, maxStopToPathDistance, maxDistanceForEliminatingVertices,
				trimPathBeforeFirstStopOfTrip);
	}
	
	public void processPathSegments(String filename) {
		// For logging how long things take
		IntervalTimer timer = new IntervalTimer();

		// Let user know what is going on
		logger.info("Processing barefoot map...");
		List<BaseRoad> map = new LinkedList<>();
		// Need to process stopPaths for every trip pattern...
		long segmentCounter=0;
		for (TripPattern tripPattern : tripPatterns) {
			// Determine the GtfsShape associated with the TripPattern
			String shapeId = tripPattern.getShapeId();
			List<GtfsShape> gtfsShapesForTripPattern = gtfsShapesMap.get(shapeId);
			
			Polyline polyLine=new Polyline();
			
			// No point in this if no shapes,txt file defined. Would be trying to match to straight lines which would be incorrect anyway.
			if (gtfsShapesForTripPattern != null) {
				
				Point startPoint = null;
				Point endPoint = null;
				for(GtfsShape shape:gtfsShapesForTripPattern)
				{										
					Line segment = new Line();
												
					if(endPoint!=null)
						startPoint=endPoint;
					else
					{
						startPoint=new Point();
						startPoint.setXY(shape.getShapePtLon(),shape.getShapePtLat());
					}
					endPoint=new Point();
					endPoint.setXY(shape.getShapePtLon(),shape.getShapePtLat());	
																			
					segment.setStart(startPoint);
					segment.setEnd(endPoint);
					
					boolean bStartNewPath = false;
					polyLine.addSegment(segment, bStartNewPath);				
				}				
			} 						
			logger.debug(GeometryEngine.geometryToGeoJson(polyLine));
			
			BaseRoad road=new BaseRoad(new Long(shapeId),segmentCounter, segmentCounter++, new Long(shapeId), true, (short) 1, 1F, 60F, 60F, 100F, (Polyline) polyLine);
			map.add(road);
		}
		BfmapWriter writer=new BfmapWriter(filename);
		writer.open();
		
		for(BaseRoad road:map)
		{
			logger.debug("Road id="+road.id() +" : "+ GeometryEngine.geometryToGeoJson(road.geometry()));
			writer.write(road);		
		}
		writer.close();
		
		// Let user know what is going on
		logger.info("Finished processing barefoot map. " +
				"Took {} msec.",
				timer.elapsedMsec());	
	}

}
