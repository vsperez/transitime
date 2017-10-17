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

package org.transitime.avl.mapmatch;

import org.transitime.db.structs.AvlReport;
import org.transitime.db.structs.Location;
/**
 * Abstract class with definition of methods required to support map matching. 
 * 
 * @author Sean Óg Crudden
 *
 */
public abstract class MapMatch {
	
	/**
	 * Gets the latest location from the map matcher for a vehicle.
	 * @param vehicleId
	 * @return Adjusted Location of the vehicle.
	 */
	public abstract Location getAdjustedLocation(String vehicleId);
	
	/**
	 * Send the latest avl report to the map matcher.
	 * @param avlReport
	 */
	public abstract void sendUpdate(AvlReport avlReport);
	
}
