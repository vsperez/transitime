package org.transitclock.ipc.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.transitclock.core.diversion.model.Diversion;
import org.transitclock.db.structs.Location;

public class IpcDiversion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2548744289428304091L;

	private String routeId;
	
	private String tripId;
	
	private String shapeId;
	
	private int startStopSeq;
	
	private int distanceStartAlongSegment;
	
	private int returnStopSeq;
	
	private int distanceEndAlongSegment;
	
	private List<Location> detourPath;
	
	private List<Location> stopLocations;
	
	private Date startTime;
	
	private Date  endTime;

	public IpcDiversion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IpcDiversion(String routeId, String tripId, String shapeId, int startStopSeq, int distanceStartAlongSegment,
			int returnStopSeq, int distanceEndAlongSegment, List<Location> detourPath, List<Location> stopLocations,
			Date startTime, Date endTime) {
		super();
		this.routeId = routeId;
		this.tripId = tripId;
		this.shapeId = shapeId;
		this.startStopSeq = startStopSeq;
		this.distanceStartAlongSegment = distanceStartAlongSegment;
		this.returnStopSeq = returnStopSeq;
		this.distanceEndAlongSegment = distanceEndAlongSegment;
		this.detourPath = detourPath;
		this.stopLocations = stopLocations;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public IpcDiversion(Diversion diversion) {

		this.routeId = diversion.getRouteId();
		this.tripId = diversion.getTripId();
		this.shapeId = diversion.getShapeId();				
		this.startStopSeq = diversion.getStartStopSeq();
		
		this.distanceStartAlongSegment = diversion.getDistanceStartAlongSegment();
		this.returnStopSeq = diversion.getReturnStopSeq();
		this.distanceEndAlongSegment = diversion.getDistanceEndAlongSegment();
		this.detourPath = diversion.getDetourPath();
		this.stopLocations = diversion.getStopLocations();
		this.startTime = diversion.getStartTime();
		this.endTime = diversion.getEndTime();
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "IpcDiversion [routeId=" + routeId + ", tripId=" + tripId + ", shapeId=" + shapeId + ", startStopSeq="
				+ startStopSeq + ", distanceStartAlongSegment=" + distanceStartAlongSegment + ", returnStopSeq="
				+ returnStopSeq + ", distanceEndAlongSegment=" + distanceEndAlongSegment + ", detourPath=" + detourPath
				+ ", stopLocations=" + stopLocations + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
}
