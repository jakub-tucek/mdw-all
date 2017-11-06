package rmi.client;

import service.api.Trip;
import service.api.TripService;

import java.rmi.Naming;

public class RMIClient {

    public static void main(String[] args) throws Exception {
        TripService client = (TripService) Naming.lookup("//localhost/Trip");

        System.out.println(client.getAllTrips());

        client.addNewTrip(new Trip(1, "name", 2));

        System.out.println(client.getAllTrips());
    }
}
