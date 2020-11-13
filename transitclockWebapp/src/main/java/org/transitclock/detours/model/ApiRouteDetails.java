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
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.transitclock.db.structs.Location;
import org.transitclock.ipc.data.IpcDirection;
import org.transitclock.ipc.data.IpcDirectionsForRoute;
import org.transitclock.ipc.data.IpcRoute;
import org.transitclock.ipc.data.IpcShape;

/**
 * Provides detailed information for a route include stops and shape info.
 *
 * @author SkiBu Smith
 *
 */
public class ApiRouteDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String shortName;
	private String longName;
	private String color;
	private String textColor;
	private String type;
	private List<ApiDirection> direction;

	private List<ApiShape> shape;

	private ApiExtent extent;

	private ApiLocation locationOfNextPredictedVehicle;

	/********************** Member Functions **************************/

    /**
     * Need a no-arg constructor for Jersey. Otherwise get really obtuse
     * "MessageBodyWriter not found for media type=application/json" exception.
     */
	protected ApiRouteDetails() {
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ApiDirection> getDirection() {
		return direction;
	}

	public void setDirection(List<ApiDirection> direction) {
		this.direction = direction;
	}

	public List<ApiShape> getShape() {
		return shape;
	}

	public void setShapes(List<ApiShape> shape) {
		this.shape = shape;
	}

	public ApiExtent getExtent() {
		return extent;
	}

	public void setExtent(ApiExtent extent) {
		this.extent = extent;
	}

	public ApiLocation getLocationOfNextPredictedVehicle() {
		return locationOfNextPredictedVehicle;
	}

	public void setLocationOfNextPredictedVehicle(ApiLocation locationOfNextPredictedVehicle) {
		this.locationOfNextPredictedVehicle = locationOfNextPredictedVehicle;
	}

	

}
