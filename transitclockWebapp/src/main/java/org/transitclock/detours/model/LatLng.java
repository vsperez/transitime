package org.transitclock.detours.model;

import java.io.Serializable;

import org.transitclock.db.structs.Location;
/**
 * To use the same information of leaflet JS library
 * @author vperez
 *
 */
public class LatLng  implements Serializable{

	@Override
	public String toString() {
		return "LatLng [lat=" + lat + ", lng=" + lng + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double lat;
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	double lng;
	public Location toLocation() {
		// TODO Auto-generated method stub
		return new Location(lat, lng);
	}

}
