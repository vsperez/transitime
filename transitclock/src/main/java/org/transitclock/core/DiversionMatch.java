package org.transitclock.core;

import org.transitclock.core.diversion.model.Diversion;
import org.transitclock.db.structs.Block;
import org.transitclock.db.structs.Location;

public class DiversionMatch extends SpatialMatch {

	public DiversionMatch(Double distanceToDiversion, Double distanceAlongDiversion, long avlTime, Block block, int tripIndex, String shapeId, String tripId, String routeId) {		
		super(avlTime, block, tripIndex);
		
		this.routeId=routeId;
		this.tripId=tripId;
		this.shapeId=shapeId;
		this.distanceAlongDiversion=distanceAlongDiversion;
		
	}

	Double distanceAlongDiversion;
	Double distanceToDiversion;
		
	Location predictedLocation;
		
	String routeId;
	String tripId;	
	String shapeId;	
	
	

	public Double getDistanceAlongDiversion() {
		return distanceAlongDiversion;
	}

	public void setDistanceAlongDiversion(Double distanceAlongDiversion) {
		this.distanceAlongDiversion = distanceAlongDiversion;
	}

	public Double getDistanceToDiversion() {
		return distanceToDiversion;
	}

	public void setDistanceToDiversion(Double distanceToDiversion) {
		this.distanceToDiversion = distanceToDiversion;
	}

	public Diversion getDiversion() {
		return diversion;
	}

	public void setDiversion(Diversion diversion) {
		this.diversion = diversion;
	}
	
	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getShapeId() {
		return shapeId;
	}

	public void setShapeId(String shapeId) {
		this.shapeId = shapeId;
	}

	public Location getPredictedLocation() {
		return predictedLocation;
	}

	public void setPredictedLocation(Location predictedLocation) {
		this.predictedLocation = predictedLocation;
	}

	Diversion diversion;
	
}
