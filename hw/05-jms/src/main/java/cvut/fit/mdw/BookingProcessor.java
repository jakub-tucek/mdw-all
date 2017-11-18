package cvut.fit.mdw;

import cvut.fit.mdw.domain.ConsumerQueueWrapper;
import cvut.fit.mdw.domain.ProducerQueueWrapper;

import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class BookingProcessor implements MessageListener {


    private ConsumerQueueWrapper bookingQWrapper;

    private ProducerQueueWrapper confirmQWrapper;

    public static void main(String[] args) throws Exception {
        BookingProcessor bookingProcessor = new BookingProcessor();

        bookingProcessor.receive();
    }

    // sends the message to the queue
    private void send(String message) throws Exception {
        TextMessage msg = confirmQWrapper.getQsession().createTextMessage();


        msg.setText(message);
        confirmQWrapper.getQsender().send(msg, DeliveryMode.PERSISTENT, 8, 0);
        System.out.println("The confirm message was sent to the destination " +
                confirmQWrapper.getQsender().getDestination().toString());
    }

    // start receiving messages from the queue
    private void receive() throws Exception {
        this.bookingQWrapper = new ConsumerQueueWrapper(this, Config.BOOKING_QUEUE);
        this.confirmQWrapper = new ProducerQueueWrapper(Config.CONFIRMATION_QUEUE);

        System.out.println("Connected to " + Config.BOOKING_QUEUE + ", receiving messages...");
        try {
            synchronized (this) {
                while (true) {
                    this.wait();
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
            System.out.println("Booking request Received: " + msgText);

            this.send(msgText + " confirmed");

        } catch (Exception jmse) {
            System.err.println("An exception occurred: " + jmse.getMessage());
        }
    }
}
