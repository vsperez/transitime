package org.transitclock.core.diversion.cache;

import org.transitclock.core.diversion.model.Diversion;

public class DiversionsKey {
	String tripId;
	String routeId;
	
	public DiversionsKey(Diversion diversion) {
		this.tripId=diversion.getTripId();
		this.routeId=diversion.getTripId();						
	}		
	
}
