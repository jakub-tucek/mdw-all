package cvut.fit.mdw;

import cvut.fit.mdw.config.Config;
import cvut.fit.mdw.domain.*;

import javax.jms.*;
import java.io.Serializable;
import java.util.stream.Stream;

public class OrderProcessor extends AbstractReceiver implements MessageListener {

    private ProducerQueueWrapper bookingQWrapper;
    private ProducerQueueWrapper tripQWrapper;

    public static void main(String[] args) throws Exception {
        OrderProcessor processor = new OrderProcessor();

        processor.receive();
    }

    // sends the message to the queue
    private void send(ProducerQueueWrapper wrapper, Serializable object) throws Exception {
        QueueSession qsession = wrapper.getQsession();
        ObjectMessage tripMsg = qsession.createObjectMessage(object);
        wrapper.getQsender().send(tripMsg, DeliveryMode.PERSISTENT, 8, 0);
    }

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Serializable object = objectMessage.getObject();

            if (object instanceof Booking) {
                System.out.println("OrderProcess: Received booking");
                send(bookingQWrapper, object);
                System.out.println("OrderProcess: Send booking");
            } else if (object instanceof Trip) {
                System.out.println("OrderProcess: Received trip");
                send(tripQWrapper, object);
                System.out.println("OrderProcess: Send trip");
            }
        } catch (Exception jmse) {
            System.err.println("OrderProcessor: An exception occurred: " + jmse.getMessage());
        }
    }

    @Override
    protected void init() throws Exception {
        this.bookingQWrapper = new ProducerQueueWrapper(Config.BOOKINGS_QUEUE);
        this.tripQWrapper = new ProducerQueueWrapper(Config.NEW_TRIPS_QUEUE);
        this.consumerQueueWrapper = new ConsumerQueueWrapper(this, Config.ALL_ORDERS_QUEUE);
    }

    @Override
    protected Stream<QueueWrapper> getAllWrappers() {
        return Stream.of(
                bookingQWrapper,
                tripQWrapper,
                consumerQueueWrapper
        );
    }
}
