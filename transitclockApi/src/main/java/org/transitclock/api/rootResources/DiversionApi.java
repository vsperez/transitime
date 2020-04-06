package org.transitclock.api.rootResources;

import java.rmi.RemoteException;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.transitclock.api.data.ApiCacheDetails;
import org.transitclock.api.data.ApiDiversion;
import org.transitclock.api.data.ApiDiversions;
import org.transitclock.api.utils.StandardParameters;
import org.transitclock.api.utils.WebUtils;
import org.transitclock.ipc.data.IpcDiversions;
import org.transitclock.ipc.interfaces.DiversionsInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@Path("/key/{key}/agency/{agency}")
public class DiversionApi {
	@Path("/command/getDiversionsByRoute")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Operation(summary="Get list of active diversions for a route.",
	description="This is to give the means of manually setting a vehicle unpredictable and unassigned so it will be reassigned quickly.",
	tags= {"command","vehicle"})
	public Response getDiversionsByRouteId(@BeanParam StandardParameters stdParameters,
			@Parameter(description="routeId to get diversions for.",required=true) @PathParam("routeId") String routeId)  throws WebApplicationException
	{
		stdParameters.validate();
		try {							
			DiversionsInterface diverionsInterface = stdParameters.getDiversionInterface();
			
			IpcDiversions ipcDiversions = diverionsInterface.getDiversionsForRoute(routeId);
			
			ApiDiversions apiDiversions=new ApiDiversions(ipcDiversions.getDiversions());
				
			return stdParameters.createResponse(apiDiversions);
			
		}  catch (Exception e) {
			// If problem getting data then return a Bad Request
			throw WebUtils.badRequestException(e);
		}
	}
	@Path("/command/getDiversionsByTrip")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Operation(summary="Get a list of active diversions for a trip",
	description="This is to give the means of manually setting a vehicle unpredictable and unassigned so it will be reassigned quickly.",
	tags= {"command","vehicle"})
	public Response getDiversionsByTripId(@BeanParam StandardParameters stdParameters,
			@Parameter(description="tripId to get diversions for.",required=true) @PathParam("tripId") String tripId) throws WebApplicationException
	{
		stdParameters.validate();
		
		try {
			DiversionsInterface diverionsInterface = stdParameters.getDiversionInterface();
			
			IpcDiversions ipcDiversions = diverionsInterface.getDiversionsForTrip(tripId);
			
			ApiDiversions apiDiversions=new ApiDiversions(ipcDiversions.getDiversions());
				
			return stdParameters.createResponse(apiDiversions);
			
		}  catch (Exception e) {
			// If problem getting data then return a Bad Request
			throw WebUtils.badRequestException(e);
		}
	}
}
