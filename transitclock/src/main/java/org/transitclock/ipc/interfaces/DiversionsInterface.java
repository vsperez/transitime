package org.transitclock.ipc.interfaces;

import java.rmi.Remote;

import org.transitclock.ipc.data.IpcDiversions;

public interface DiversionsInterface  extends Remote {
	IpcDiversions getDiversionsForTrip(String tripId);
	IpcDiversions getDiversionsForRoute(String routeId);
}
