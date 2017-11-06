package service;
import service.api.Trip;

import java.util.Collection;
import java.util.Hashtable;

public class DB {
    private static DB instance = null;
    private Hashtable<Integer, Trip> ht = new Hashtable<>();
 
    public static DB getInstance() {
        if (instance == null)
            instance = new DB();
        return instance;
    }
    public void addTrip(Trip t){
        ht.put(t.getId(), t);
    }
    public Trip getObject(int id) {
        return ht.get(id);
    }
    
    public Collection<Trip> getTrips() {
    	return ht.values();
    }
	public void addBooking(int tripId, String name) {
		Trip t = ht.get(tripId);
		
		if (t.getBookings().size() < t.getCapacity()) {
			t.addPerson(name);
		}
	}
   
}
