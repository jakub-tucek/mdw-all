package cz.fit.mdw;

import cz.fit.mdw.domain.ProducerQueueWrapper;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class JMSProducer {

    // a message that will be sent to the queue
    private TextMessage msg;
    private ProducerQueueWrapper queueWrapper;


    // sends the message to the queue
    public void send(String queueName, String message) throws Exception {

        queueWrapper = new ProducerQueueWrapper(queueName);
        msg = queueWrapper.getQsession().createTextMessage();

        // send the message and close
        try {
            msg.setText(message);
            queueWrapper.getQsender().send(msg, DeliveryMode.PERSISTENT, 8, 0);
            System.out.println("The message was sent to the destination " +
                    queueWrapper.getQsender().getDestination().toString());
        } finally {
            queueWrapper.close();
        }
    }

    public static void main(String[] args) throws Exception {
        // input arguments
        String msg = "Hello" ;
        String queueName = "jms/mdw-queue";

        // create the producer object and send the message
        JMSProducer producer = new JMSProducer();
        producer.send(queueName, msg);
    }

}
