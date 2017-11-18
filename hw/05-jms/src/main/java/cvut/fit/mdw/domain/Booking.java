package cvut.fit.mdw.domain;

import java.io.Serializable;

public class Booking implements Serializable {

    private final String tripName;

    private final String name;


    public Booking(String tripName, String name) {
        this.tripName = tripName;
        this.name = name;
    }

    public String getTripName() {
        return tripName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "tripName='" + tripName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

