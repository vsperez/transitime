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

import org.transitime.config.StringConfigValue;

import org.transitime.utils.ClassInstantiator;
/**
 * Factory to give access to the configured Map Matcher.
 * 
 * @author Sean Óg Crudden
 *
 */
public class MapMatchFactory {
	private static StringConfigValue className = 
			new StringConfigValue("transitime.core.avl.mapMatchClass", 
					"org.transitime.avl.mapmatch.BarefootMapMatcher",
					"Specifies the class to use to get a map match.");
	
	private static MapMatch singleton = null;
	
	public static MapMatch getInstance() {
		
		if (singleton == null && className!=null ) {
			singleton = ClassInstantiator.instantiate(className.getValue(), 
					MapMatch.class);
		}		
		return singleton;
	}
}
