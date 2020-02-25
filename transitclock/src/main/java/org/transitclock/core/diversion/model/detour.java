package org.transitclock.core.diversion.model;

import java.util.List;

import org.transitclock.db.structs.Location;
import org.transitclock.db.structs.Route;
import org.transitclock.db.structs.Trip;

/**
 * @author Sean Óg Crudden
 * This is the starting point of modeling a detour. 
 */
public class detour {
	
	private Route routeId;
	
	private Trip tripId;
	
	private int startStopSeq;
	
	private int distanceStartAlongSegment;
	
	private int returnStopSeq;
	
	private int distanceEndAlongSegment;
	
	private List<Location> detourPath;
	
	private List<Location> stopLocations;

	public detour(Route routeId, Trip tripId, int startStopSeq, int distanceStartAlongSegment, int returnStopSeq,
			int distanceEndAlongSegment, List<Location> detourPath, List<Location> stopLocations) {
		super();
		this.routeId = routeId;
		this.tripId = tripId;
		this.startStopSeq = startStopSeq;
		this.distanceStartAlongSegment = distanceStartAlongSegment;
		this.returnStopSeq = returnStopSeq;
		this.distanceEndAlongSegment = distanceEndAlongSegment;
		this.detourPath = detourPath;
		this.stopLocations = stopLocations;
	}

	public Route getRouteId() {
		return routeId;
	}

	public void setRouteId(Route routeId) {
		this.routeId = routeId;
	}

	public Trip getTripId() {
		return tripId;
	}

	public void setTripId(Trip tripId) {
		this.tripId = tripId;
	}

	public int getStartStopSeq() {
		return startStopSeq;
	}

	public void setStartStopSeq(int startStopSeq) {
		this.startStopSeq = startStopSeq;
	}

	public int getDistanceStartAlongSegment() {
		return distanceStartAlongSegment;
	}

	public void setDistanceStartAlongSegment(int distanceStartAlongSegment) {
		this.distanceStartAlongSegment = distanceStartAlongSegment;
	}

	public int getReturnStopSeq() {
		return returnStopSeq;
	}

	public void setReturnStopSeq(int returnStopSeq) {
		this.returnStopSeq = returnStopSeq;
	}

	public int getDistanceEndAlongSegment() {
		return distanceEndAlongSegment;
	}

	public void setDistanceEndAlongSegment(int distanceEndAlongSegment) {
		this.distanceEndAlongSegment = distanceEndAlongSegment;
	}

	public List<Location> getDetourPath() {
		return detourPath;
	}

	public void setDetourPath(List<Location> detourPath) {
		this.detourPath = detourPath;
	}

	public List<Location> getStopLocations() {
		return stopLocations;
	}

	public void setStopLocations(List<Location> stopLocations) {
		this.stopLocations = stopLocations;
	}
	
	
		
}
