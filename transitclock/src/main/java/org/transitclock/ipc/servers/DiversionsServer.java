package org.transitclock.ipc.servers;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IpcDiversions getDiversionsForRoute(String routeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
