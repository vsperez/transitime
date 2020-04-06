package org.transitclock.ipc.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.transitclock.ipc.data.IpcDiversions;

public interface DiversionsInterface  extends Remote {
	IpcDiversions getDiversionsForTrip(String tripId)  throws RemoteException;
	IpcDiversions getDiversionsForRoute(String routeId)  throws RemoteException;
}
