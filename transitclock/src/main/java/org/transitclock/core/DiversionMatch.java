package org.transitclock.core;

import org.transitclock.core.diversion.model.Diversion;
import org.transitclock.db.structs.Block;
import org.transitclock.db.structs.Location;

public class DiversionMatch extends SpatialMatch {

	public DiversionMatch(long avlTime, Block block, int tripIndex) {		
		super(avlTime, block, tripIndex);
	}

	int distanceAlongDiversion;
	int distanceToDiversion;
	Location predictedLocation;
	
	public int getDistanceAlongDiversion() {
		return distanceAlongDiversion;
	}

	public void setDistanceAlongDiversion(int distanceAlongDiversion) {
		this.distanceAlongDiversion = distanceAlongDiversion;
	}

	public int getDistanceToDiversion() {
		return distanceToDiversion;
	}

	public void setDistanceToDiversion(int distanceToDiversion) {
		this.distanceToDiversion = distanceToDiversion;
	}

	public Diversion getDiversion() {
		return diversion;
	}

	public void setDiversion(Diversion diversion) {
		this.diversion = diversion;
	}

	Diversion diversion;
	
}
