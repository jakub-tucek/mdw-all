package cvut.fit.mdw.domain;

import java.io.Serializable;

public class Trip implements Serializable {

    private final String tripName;

    private final int capacity;

    public Trip(String tripName, int capacity) {
        this.tripName = tripName;
        this.capacity = capacity;
    }

    public String getTripName() {
        return tripName;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripName='" + tripName + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
