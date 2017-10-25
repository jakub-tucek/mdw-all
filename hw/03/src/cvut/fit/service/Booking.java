package cvut.fit.service;

public class Booking {

    private BookingState state;

    private String fullName;

    private int price;

    private String paymentId;

    public Booking(String fullName, int price) {
        this.fullName = fullName;
        this.price = price;
        this.state = BookingState.NEW;
    }


    public void pay(String paymentId) {
        this.state = BookingState.PAYMENT;
        this.paymentId = paymentId;
    }


    public BookingState getState() {
        return state;
    }

    public String getFullName() {
        return fullName;
    }

    public int getPrice() {
        return price;
    }

    public String getPaymentId() {
        return paymentId;
    }


    @Override
    public String toString() {
        return "Booking{" +
                "state=" + state +
                ", fullName='" + fullName + '\'' +
                ", price=" + price +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }
}
