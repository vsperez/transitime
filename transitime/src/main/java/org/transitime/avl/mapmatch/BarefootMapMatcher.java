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

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.transitime.config.IntegerConfigValue;
import org.transitime.core.AvlProcessor;
import org.transitime.db.structs.AvlReport;
import org.transitime.db.structs.Location;

import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.WktImportFlags;
import com.esri.core.geometry.Geometry.Type;
/**
 * Implementation of MapMatch using barefoot.
 * @see org.transitime.avl.mapmatch.MapMatch
 * @author Sean Óg Crudden
 *
 */
public class BarefootMapMatcher extends MapMatch {

	private static IntegerConfigValue barefootPortNumber = new IntegerConfigValue("transitime.core.avl.barefoot.port",
			1234, "Specifies the port to talk to the barefoot server on.");

	private static final Logger logger = LoggerFactory.getLogger(BarefootMapMatcher.class);

	@Override
	public Location getAdjustedLocation(String vehicleId) {

		Location result = null;
		try {
			JSONObject state = requestState(vehicleId);
			JSONArray candidatesArray = state.getJSONArray("candidates");

			Double maxProbability = 0.0;
			for (int i = 0; i < candidatesArray.length(); i++) {
				double probabililty = candidatesArray.getJSONObject(i).getDouble("prob");

				if (probabililty > maxProbability) {
					String geoPoint = candidatesArray.getJSONObject(i).getString("point");
					Point point = (Point) GeometryEngine.geometryFromWkt(geoPoint, WktImportFlags.wktImportDefaults,
							Type.Point);

					result = new Location(point.getY(), point.getX());

					maxProbability = probabililty;
				}
			}
		} catch (Exception e) {
			logger.error("Failed to adjust location with barefoot. Reason: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	private JSONObject requestState(String id) throws JSONException, InterruptedException, IOException {
		int trials = 120;
		int timeout = 500;
		Socket client = null;

		InetAddress host = InetAddress.getLocalHost();

		int port = barefootPortNumber.getValue();
		// TODO Will need to leave socket open.
		while (client == null || !client.isConnected()) {
			try {
				client = new Socket(host, port);
			} catch (IOException e) {
				Thread.sleep(timeout);

				if (trials == 0) {
					logger.error(e.getMessage());
					client.close();
					throw new IOException();
				} else {
					trials -= 1;
				}
			}
		}

		JSONObject json = new JSONObject();
		json.put("id", id);

		PrintWriter writer = new PrintWriter(client.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		writer.println(json.toString());
		writer.flush();

		String code = reader.readLine();
		if (code.contentEquals("SUCCESS")) {
			String response = reader.readLine();
			return new JSONObject(response);

		}
		reader.close();
		return null;
	}

	@Override
	public void sendUpdate(AvlReport avlReport) {
		try {
			JSONObject report = new JSONObject();

			InetAddress host = InetAddress.getLocalHost();

			int port = barefootPortNumber.getValue();

			report.put("id", avlReport.getVehicleId());

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");

			report.put("time", df.format(avlReport.getDate()));
			
			Point point=new Point();
			point.setX(avlReport.getLon());
			point.setY(avlReport.getLat());
			
			report.put("point", "POINT(" + avlReport.getLon() + " " + avlReport.getLat() + ")");
			//report.put("point", GeometryEngine.geometryToGeoJson(point));

			sendBareFootSample(host, port, report);

		} catch (Exception e) {
			logger.error("Problem when sending samples to barefoot.", e);
		}
	}

	private void sendBareFootSample(InetAddress host, int port, JSONObject sample) throws Exception {
		int trials = 120;
		int timeout = 500;
		Socket client = null;
		// TODO Will need to leave socket open.
		while (client == null || !client.isConnected()) {
			try {
				client = new Socket(host, port);
			} catch (IOException e) {
				Thread.sleep(timeout);

				if (trials == 0) {
					logger.error(e.getMessage());
					client.close();
					throw new IOException();
				} else {
					trials -= 1;
				}
			}
		}
		PrintWriter writer = new PrintWriter(client.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		writer.println(sample.toString());
		writer.flush();

		String code = reader.readLine();
		if (!code.equals("SUCCESS")) {
			throw new Exception("Barefoot server did not respond with SUCCESS");
		}
	}
}
