package cvut.fit.mdw;

import cvut.fit.mdw.config.Config;
import cvut.fit.mdw.domain.Booking;
import cvut.fit.mdw.domain.ProducerQueueWrapper;
import cvut.fit.mdw.domain.Trip;

import javax.jms.DeliveryMode;
import javax.jms.ObjectMessage;
import javax.jms.QueueSession;

public class OrderClient {

    private ProducerQueueWrapper queueWrapper;


    public OrderClient() throws Exception {
        queueWrapper = new ProducerQueueWrapper(Config.ALL_ORDERS_QUEUE);
    }

    public static void main(String[] args) throws Exception {
        new OrderClient().sendAll();
    }

    private void sendAll() throws Exception {
        try {
            QueueSession qsession = queueWrapper.getQsession();

            ObjectMessage tripMsg = qsession.createObjectMessage(new Trip("trip name1", 99));
            ObjectMessage bookingMsg = qsession.createObjectMessage(new Booking("trip name1", "JT"));


            System.out.println("Client: Sending trip");
            queueWrapper.getQsender().send(tripMsg, DeliveryMode.PERSISTENT, 8, 0);
            System.out.println("Client: Sending booking");
            queueWrapper.getQsender().send(bookingMsg, DeliveryMode.PERSISTENT, 8, 0);

            System.out.println("Client: Objects sent");
        } finally {
            queueWrapper.close();
        }
    }
}
