package cvut.fit.service;

public class Record {

    private int id;
    private String type;
    private String location;

    private Integer capacity;
    private Integer occupied;
    private Integer trip;
    private String person;


    public int getId() {
        return id;
    }

    public Record setId(int id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Record setType(String type) {
        this.type = type;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Record setLocation(String location) {
        this.location = location;
        return this;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Record setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public Integer getOccupied() {
        return occupied;
    }

    public Record setOccupied(Integer occupied) {
        this.occupied = occupied;
        return this;
    }

    public Integer getTrip() {
        return trip;
    }

    public Record setTrip(Integer trip) {
        this.trip = trip;
        return this;
    }

    public String getPerson() {
        return person;
    }

    public Record setPerson(String person) {
        this.person = person;
        return this;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", occupied=" + occupied +
                ", trip=" + trip +
                ", person='" + person + '\'' +
                '}';
    }
}
