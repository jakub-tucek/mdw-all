package rmi.server;

import service.DB;
import service.TripSeviceImpl;
import service.api.Trip;
import service.api.TripService;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class RMIServer extends UnicastRemoteObject implements TripService {

    private Connection connection;

    public void init() throws Exception {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        Class.forName(driver).newInstance();
        String protocol = "jdbc:derby:";
        connection = DriverManager.getConnection(protocol + "derbyDB;create=true");
        initData();

    }

    private void initData() throws Exception {
        DatabaseMetaData dbmd = connection.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, "TRIP", null);
        if (rs.next()) {
            System.out.println("Table " +  rs.getString(3) + " exists");
        } else {
            Statement stmt = connection.createStatement();
            String query = "CREATE TABLE TRIP (ID INT PRIMARY KEY, NAME VARCHAR(12), CAPACITY INT)";
            stmt.executeUpdate(query);
            stmt.close();

            stmt = connection.createStatement();
            query = "INSERT INTO TRIP VALUES (1, 'trip name', 3),(30,'THIRTY', 4)";
            stmt.executeUpdate(query);
            stmt.close();
        }
    }

    private RMIServer() throws RemoteException {
        super();
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException();
        }
    }

    public static void main(String[] args) {
        try {
            RMIServer server = new RMIServer();
            Naming.rebind("//0.0.0.0/Trip", server);

            System.out.println("Server started...");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }

    @Override
    public Collection<Trip> getAllTrips() throws RemoteException {
        try {
            ArrayList<Trip> objects = new ArrayList<>();

            Statement stmt = connection.createStatement();
            String query = "SELECT ID, NAME, CAPACITY FROM TRIP";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                objects.add(new Trip(rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getInt("CAPACITY")));
            }

            stmt.close();
            return objects;
        }catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException();
        }
    }

    @Override
    public void addNewTrip(Trip trip) throws RemoteException{
        try {
            Statement stmt = connection.createStatement();
            String query = String.format("INSERT INTO TRIP VALUES (%s, '%s', %s);",
                            trip.getId(), trip.getName(), trip.getCapacity());
            stmt.executeUpdate(query);
            stmt.close();
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }

    }
}
