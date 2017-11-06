package service.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface TripService extends Remote {

    Collection<Trip> getAllTrips() throws RemoteException;

    void addNewTrip(Trip trip) throws RemoteException;
}
