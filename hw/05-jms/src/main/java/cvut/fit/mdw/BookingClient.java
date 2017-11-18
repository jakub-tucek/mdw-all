package cvut.fit.mdw;

import cvut.fit.mdw.domain.ConsumerQueueWrapper;
import cvut.fit.mdw.domain.ProducerQueueWrapper;

import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class BookingClient implements MessageListener {


    private ProducerQueueWrapper bookingQWrapper;

    private ConsumerQueueWrapper confirmQWrapper;


    private boolean waitForConfirm = true;

    public static void main(String[] args) throws Exception {
        BookingClient bookingProcessor = new BookingClient();

        bookingProcessor.receive();
    }

    // sends the message to the queue
    private void send() throws Exception {
        TextMessage msg = confirmQWrapper.getQsession().createTextMessage();


        msg.setText("Request booking");
        bookingQWrapper.getQsender().send(msg, DeliveryMode.PERSISTENT, 8, 0);
        System.out.println("The booking message was sent to the destination " +
                bookingQWrapper.getQsender().getDestination().toString());
    }

    // start receiving messages from the queue
    private void receive() throws Exception {
        this.bookingQWrapper = new ProducerQueueWrapper(Config.BOOKING_QUEUE);
        this.confirmQWrapper = new ConsumerQueueWrapper(this, Config.CONFIRMATION_QUEUE);

        System.out.println("Connected to " + Config.CONFIRMATION_QUEUE + ", receiving messages...");

        try {
            this.send();

            synchronized (this) {
                while (waitForConfirm) {
                    this.wait(10000);
                }
            }
        } finally {
            bookingQWrapper.close();
            confirmQWrapper.close();
            System.out.println("Finished.");
        }
    }

    public void onMessage(Message msg) {
        try {
            String msgText;
            if (msg instanceof TextMessage) {
                msgText = ((TextMessage) msg).getText();
            } else {
                msgText = msg.toString();
            }
            System.out.println("Booking request confirmation received: " + msgText);
            waitForConfirm = false;
        } catch (Exception jmse) {
            System.err.println("An exception occurred: " + jmse.getMessage());
        }
    }
}
