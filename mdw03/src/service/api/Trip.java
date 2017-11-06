package service.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Trip implements Serializable {
    private String name;
    private int id;
    private int capacity;
    private List<String> bookings = new ArrayList<>();
 
    public Trip(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
    
    
    public int getCapacity() {
    	return capacity;
    }
    
    public List<String> getBookings() {
    	return bookings;
    }
    
    public void addPerson(String name) {
    	bookings.add(name);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", capacity=" + capacity +
                ", bookings=" + bookings +
                '}';
    }
}
