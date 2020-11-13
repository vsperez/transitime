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
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.transitclock.db.structs.Location;
import org.transitclock.ipc.data.IpcShape;
import org.transitclock.utils.Geo;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * A portion of a shape that defines a trip pattern. A List of ApiLocation
 * objects.
 *
 * @author SkiBu Smith
 *
 */
public class ApiShape implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonAlias("tripPattern")
	private String tripPatternId;

	private String headsign;

	// For indicating that in UI should deemphasize this shape because it
	// is not on a main trip pattern.
	private Boolean minor;
	@JsonAlias("loc")
	private List<ApiLocation> locations;
	private double length;
	private String directionId;
	
	//To define what kind of pattern is: circular (loop, one ending), linear (normal line with two different endings)
	@XmlAttribute
	private String patternType="linear";

	private static final int LOOP_ENDING_MAX_DISTANCE=200;
	private static final String LOOP_PATTERN="circular";
	private static final String LINAR_PATTER="linear";
	/********************** Member Functions **************************/

	/**
	 * Need a no-arg constructor for Jersey. Otherwise get really obtuse
	 * "MessageBodyWriter not found for media type=application/json" exception.
	 */
	protected ApiShape() {
	}

	public String getTripPatternId() {
		return tripPatternId;
	}

	public void setTripPatternId(String tripPatternId) {
		this.tripPatternId = tripPatternId;
	}

	public String getHeadsign() {
		return headsign;
	}

	public void setHeadsign(String headsign) {
		this.headsign = headsign;
	}

	public Boolean getMinor() {
		return minor;
	}

	public void setMinor(Boolean minor) {
		this.minor = minor;
	}

	public List<ApiLocation> getLocations() {
		return locations;
	}

	public void setLocations(List<ApiLocation> locations) {
		this.locations = locations;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getDirectionId() {
		return directionId;
	}

	public void setDirectionId(String directionId) {
		this.directionId = directionId;
	}

	public String getPatternType() {
		return patternType;
	}

	public void setPatternType(String patternType) {
		this.patternType = patternType;
	}

	public static int getLoopEndingMaxDistance() {
		return LOOP_ENDING_MAX_DISTANCE;
	}

	public static String getLoopPattern() {
		return LOOP_PATTERN;
	}

	public static String getLinarPatter() {
		return LINAR_PATTER;
	}

	

}
