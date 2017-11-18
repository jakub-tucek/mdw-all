package cvut.fit.mdw;

import cvut.fit.mdw.config.Config;
import cvut.fit.mdw.domain.ConsumerQueueWrapper;
import cvut.fit.mdw.domain.QueueWrapper;
import cvut.fit.mdw.domain.Trip;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.stream.Stream;

public class TripProcessor extends AbstractReceiver implements MessageListener {

    public static void main(String[] args) throws Exception {
        TripProcessor processor = new TripProcessor();

        processor.receive();
    }

    @Override
    protected void init() throws Exception {
        this.consumerQueueWrapper = new ConsumerQueueWrapper(this, Config.NEW_TRIPS_QUEUE);
    }

    @Override
    protected Stream<QueueWrapper> getAllWrappers() {
        return Stream.of(consumerQueueWrapper);
    }

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Trip object = (Trip) objectMessage.getObject();

            System.out.println("TripProcessor: Received trip " + object.toString());
        } catch (Exception jmse) {
            System.err.println("TripProcessor: An exception occurred: " + jmse.getMessage());
        }
    }

}
