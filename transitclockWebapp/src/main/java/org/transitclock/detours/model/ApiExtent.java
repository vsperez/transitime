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

/**
 * Describes the extent of a route or agency via a min & max lat & lon.
 *
 * @author SkiBu Smith
 *
 */
public class ApiExtent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double minLat;

	private double minLon;

	public double getMinLat() {
		return minLat;
	}

	public void setMinLat(double minLat) {
		this.minLat = minLat;
	}

	public double getMinLon() {
		return minLon;
	}

	public void setMinLon(double minLon) {
		this.minLon = minLon;
	}

	public double getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(double maxLat) {
		this.maxLat = maxLat;
	}

	public double getMaxLon() {
		return maxLon;
	}

	public void setMaxLon(double maxLon) {
		this.maxLon = maxLon;
	}

	private double maxLat;

	private double maxLon;

	/********************** Member Functions **************************/

	/**
	 * Need a no-arg constructor for Jersey. Otherwise get really obtuse
	 * "MessageBodyWriter not found for media type=application/json" exception.
	 */
	protected ApiExtent() {
	}

//	public ApiExtent(Extent extent) {
//		this.minLat = MathUtils.round(extent.getMinLat(), 5);
//		this.minLon = MathUtils.round(extent.getMinLon(), 5);
//		this.maxLat = MathUtils.round(extent.getMaxLat(), 5);
//		this.maxLon = MathUtils.round(extent.getMaxLon(), 5);
//	}

}
