/*
 * This file is part of Transitime.org
 * 
 * Transitime.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL) as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * Transitime.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Transitime.org .  If not, see <http://www.gnu.org/licenses/>.
 */

package org.transitclock.detours.model;

import java.io.Serializable;

/**
 * Full description of a stop.
 * <p>
 * Note: extending from ApiLocation since have a lat & lon. Would be nice to
 * have ApiLocation as a member but when try this get a internal server 500
 * error.
 *
 * @author SkiBu Smith
 *
 */
public class ApiStop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "ApiStop [id=" + id + ", name=" + name + ", code=" + code + ", minor=" + minor + ", pathLength="
				+ pathLength + "]";
	}


	private String id;

	private String name;

	private Integer code;

	// For indicating that in UI should deemphasize this stop because it
	// is not on a main trip pattern.
	private Boolean minor;
	private Double pathLength;


	private Double lat;
	public Double getLat() {
		return lat;
	}


	public void setLat(Double lat) {
		this.lat = lat;
	}


	private Double lon;
	
	public Double getLon() {
		return lon;
	}


	public void setLon(Double lon) {
		this.lon = lon;
	}


	protected ApiStop() {
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}


	public Boolean getMinor() {
		return minor;
	}


	public void setMinor(Boolean minor) {
		this.minor = minor;
	}


	public Double getPathLength() {
		return pathLength;
	}


	public void setPathLength(Double pathLength) {
		this.pathLength = pathLength;
	}

	

}
