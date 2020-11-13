package org.transitclock.detours.model;

import java.io.Serializable;
import java.util.Arrays;

public class DetourInformation implements Serializable {

	
	@Override
	public String toString() {
		return "DetourInformation [latLngs=" + Arrays.toString(latLngs) + ", routes=" + routes + ", selectedDirection="
				+ selectedDirection + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LatLng[] getLatLngs() {
		return latLngs;
	}
	public void setLatLngs(LatLng[] latLngs) {
		this.latLngs = latLngs;
	}
	LatLng latLngs[];
	ApiRoutes routes;
	public ApiRoutes getRoutes() {
		return routes;
	}
	public void setRoutes(ApiRoutes routes) {
		this.routes = routes;
	}
	String selectedDirection;
	public String getSelectedDirection() {
		return selectedDirection;
	}
	public void setSelectedDirection(String selectedDirection) {
		this.selectedDirection = selectedDirection;
	}
	
}
