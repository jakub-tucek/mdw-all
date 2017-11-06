package service;

import service.api.Trip;
import service.api.TripService;

import java.util.Collection;

public class TripSeviceImpl implements TripService {

    @Override
    public Collection<Trip> getAllTrips() {
        return DB.getInstance().getTrips();
    }

    @Override
    public void addNewTrip(Trip trip) {
        DB.getInstance().addTrip(trip);
    }
}
