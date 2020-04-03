package org.transitclock.ipc.servers;

import java.util.ArrayList;
import java.util.List;

import org.transitclock.applications.Core;
import org.transitclock.core.diversion.cache.DiversionsCacheFactory;
import org.transitclock.core.diversion.cache.DiversionsKey;
import org.transitclock.core.diversion.cache.DiversionsList;
import org.transitclock.core.diversion.model.Diversion;
import org.transitclock.db.structs.Trip;
import org.transitclock.ipc.data.IpcDiversion;
import org.transitclock.ipc.data.IpcDiversions;
import org.transitclock.ipc.interfaces.DiversionsInterface;
import org.transitclock.ipc.rmi.AbstractServer;

public class DiversionsServer extends AbstractServer implements DiversionsInterface {

	protected DiversionsServer(String agencyId, String objectName) {
		super(agencyId, objectName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IpcDiversions getDiversionsForTrip(String tripId) {
		Core core = Core.getInstance();
		Trip trip = core.getDbConfig().getTrip(tripId);
		DiversionsKey key=new DiversionsKey(tripId, trip.getRouteId());
		
		DiversionsList diversions = DiversionsCacheFactory.getInstance().getDiversions(key);
		
		List<IpcDiversion> ipcDiversions=new ArrayList<IpcDiversion>();
		
		for( Diversion diversion:diversions.getDiversions())
		{
			ipcDiversions.add(new IpcDiversion(diversion));
		}
		
		return new IpcDiversions(ipcDiversions);
	}

	@Override
	public IpcDiversions getDiversionsForRoute(String routeId) {
		// TODO Auto-generated method stub
		DiversionsCacheFactory.getInstance();
		return null;
	}

}
