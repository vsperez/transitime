/*
 * This file is part of Transitime.org
 * 
 * Transitime.org is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License (GPL) as published by the
 * Free Software Foundation, either version 3 of the License, or any later
 * version.
 * 
 * Transitime.org is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Transitime.org . If not, see <http://www.gnu.org/licenses/>.
 */

package org.transitclock.detours.model;

import java.io.Serializable;

import org.transitclock.db.structs.Location;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A simple latitude/longitude.
 * <p>
 * This is a non-transient implementation of ApiTransientLocation. By not being
 * transient this class can be used to output a location as an element (as
 * opposed to an attribute). By inheriting from ApiTransientLocation don't need
 * to duplicate any code.
 *
 * @author SkiBu Smith
 *
 */
public class ApiLocation implements Serializable {

	/********************** Member Functions **************************/

	/**
	 * 
	 */
	private static final long serialVersionUID = -3324838075359867889L;

	/**
	 * Need a no-arg constructor for Jersey. Otherwise get really obtuse
	 * "MessageBodyWriter not found for media type=application/json" exception.
	 */
	protected ApiLocation() {
	}

	private double lat;

	private double lon;
	
	public double getLat() {
		return lat;
	}


	public void setLat(double lat) {
		this.lat = lat;
	}


	public double getLon() {
		return lon;
	}


	public void setLon(double lon) {
		this.lon = lon;
	}
	@JsonIgnore
	public Location toLocation()
	{
		return new Location(lat,lon);
	}

	@Override
	public String toString() {
		return "ApiLocation [lat=" + lat + ", lon=" + lon + "]";
	}

}
