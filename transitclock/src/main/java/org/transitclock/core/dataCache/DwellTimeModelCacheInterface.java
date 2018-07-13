package org.transitclock.core.dataCache;

import org.transitclock.core.HeadwayDetails;
import org.transitclock.core.Indices;
import org.transitclock.db.structs.ArrivalDeparture;
import org.transitclock.db.structs.Headway;

public interface DwellTimeModelCacheInterface {
	
	void addSample(Indices indices, Headway headway, long dwellTime);
	
	void addSample(ArrivalDeparture departure);
	
	long predictDwellTime(Indices indices, HeadwayDetails headway);

	
}