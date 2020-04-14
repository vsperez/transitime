package org.transitclock.api.data;

import java.util.ArrayList;

import org.transitclock.ipc.data.IpcDiversionStopPath;
import org.transitclock.ipc.data.IpcLocation;

public class ApiDiversionStopPath {
	private String stopId;
	private String stopName;
	private Integer stopSequence;
	private ApiLocation stopLocation;
	private String directionId;
	private ArrayList<ApiLocation> path = new ArrayList<ApiLocation>();
	
	public ApiDiversionStopPath() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ApiDiversionStopPath(String stopId, String stopName, Integer stopSequence, ApiLocation stopLocation,
			String directionId, ArrayList<ApiLocation> path) {
		super();
		this.stopId = stopId;
		this.stopName = stopName;
		this.stopSequence = stopSequence;
		this.stopLocation = stopLocation;
		this.directionId = directionId;
		this.path = path;
	}
	public ApiDiversionStopPath(IpcDiversionStopPath diversionStopPath)
	{
		this.stopId=diversionStopPath.getStopId();
		this.stopName=diversionStopPath.getStopName();
		this.stopSequence=diversionStopPath.getStopSequence();
		this.stopLocation=new ApiLocation(diversionStopPath.getStopLocation());
		this.directionId=diversionStopPath.getDirectionId();
		for(IpcLocation point:diversionStopPath.getPath()) {
			path.add(new ApiLocation(point));
		}
	}
	public String getStopId() {
		return stopId;
	}
	public void setStopId(String stopId) {
		this.stopId = stopId;
	}
	public String getStopName() {
		return stopName;
	}
	public void setStopName(String stopName) {
		this.stopName = stopName;
	}
	public Integer getStopSequence() {
		return stopSequence;
	}
	public void setStopSequence(Integer stopSequence) {
		this.stopSequence = stopSequence;
	}
	public ApiLocation getStopLocation() {
		return stopLocation;
	}
	public void setStopLocation(ApiLocation stopLocation) {
		this.stopLocation = stopLocation;
	}
	public String getDirectionId() {
		return directionId;
	}
	public void setDirectionId(String directionId) {
		this.directionId = directionId;
	}
	public ArrayList<ApiLocation> getPath() {
		return path;
	}
	public void setPath(ArrayList<ApiLocation> path) {
		this.path = path;
	}
	
}
