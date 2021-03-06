package cvut.fit.mdw;

import cvut.fit.mdw.config.Config;
import cvut.fit.mdw.domain.Booking;
import cvut.fit.mdw.domain.ConsumerQueueWrapper;
import cvut.fit.mdw.domain.QueueWrapper;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.stream.Stream;

public class BookingProcessor extends AbstractReceiver implements MessageListener {


    public static void main(String[] args) throws Exception {
        BookingProcessor processor = new BookingProcessor();

        processor.receive();
    }

    @Override
    protected void init() throws Exception {
        this.consumerQueueWrapper = new ConsumerQueueWrapper(this, Config.BOOKINGS_QUEUE);
    }

    @Override
    protected Stream<QueueWrapper> getAllWrappers() {
        return Stream.of(consumerQueueWrapper);
    }

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Booking object = (Booking) objectMessage.getObject();

            System.out.println("BookingProcessor: Received booking " + object.toString());
        } catch (Exception jmse) {
            System.err.println("BookingProcessor: An exception occurred: " + jmse.getMessage());
        }
    }
}
