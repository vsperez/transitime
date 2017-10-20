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

package org.transitime.reports;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.transitime.reports.ChartJsonBuilder.RowBuilder;
import org.transitime.statistics.Statistics;

/**
 * For doing SQL query and generating JSON data for a stop sequence 
 * scatter plot.
 *
 * @author Sean Óg Crudden
 *
 */
public class StopSequenceQuery extends GenericJsonQuery {

	String agencyId;
	public StopSequenceQuery(String agencyId) throws SQLException {		
		super(agencyId);
		this.agencyId=agencyId;
	}

	private static final Logger logger = LoggerFactory
			.getLogger(StopSequenceQuery.class);

	public String getJsonString(String beginDate, String beginTime, String endTime, String vehicleId) throws SQLException
	{
		String sql = "select extract ('epoch' from time), gtfsstopseq   from arrivalsdepartures where vehicleid='"+vehicleId+"'";
		String result= ChartGenericJsonQuery.getJsonString(agencyId, sql);
		return result;
	
	}
	
}
