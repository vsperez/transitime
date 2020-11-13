package org.transitclock.detours.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.transitclock.ipc.data.IpcDiversionStopPath;
import org.transitclock.ipc.data.IpcLocation;

@XmlRootElement
public class ApiDiversionStopPath implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3943658440515490430L;
	@XmlAttribute
	private String stopId;
	@XmlAttribute
	private String stopName;
	@XmlAttribute
	private Integer stopSequence;
	@XmlAttribute
	private ApiLocation stopLocation;
	@XmlAttribute
	private String directionId;
	@XmlElement(name = "path")
	private ArrayList<ApiLocation> path = new ArrayList<ApiLocation>();
	
	protected ApiDiversionStopPath() {
		
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
	
	public String getStopId() {
		return stopId;
	}

	public String getStopName() {
		return stopName;
	}

	public Integer getStopSequence() {
		return stopSequence;
	}

	public ApiLocation getStopLocation() {
		return stopLocation;
	}

	public String getDirectionId() {
		return directionId;
	}

	public ArrayList<ApiLocation> getPath() {
		return path;
	}

	
}
