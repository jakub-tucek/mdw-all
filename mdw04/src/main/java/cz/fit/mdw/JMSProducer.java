package cz.fit.mdw;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class JMSProducer {

    // connection factory
    private QueueConnectionFactory qconFactory;

    // connection to a queue
    private QueueConnection qcon;

    // session within a connection
    private QueueSession qsession;

    // queue sender that sends a message to the queue
    private QueueSender qsender;

    // queue where the message will be sent to
    private Queue queue;

    // a message that will be sent to the queue
    private TextMessage msg;

    // create a connection to the WLS using a JNDI context
    public void init(Context ctx, String queueName)
            throws NamingException, JMSException {

        // create connection factory based on JNDI and a connection
        qconFactory = (QueueConnectionFactory) ctx.lookup(Config.JMS_FACTORY);
        qcon = qconFactory.createQueueConnection();

        // create a session within a connection
        qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        // lookups the queue using the JNDI context
        queue = (Queue) ctx.lookup(queueName);

        // create sender and message
        qsender = qsession.createSender(queue);
        msg = qsession.createTextMessage();
    }

    // close sender, connection and the session
    public void close() throws JMSException {
        qsender.close();
        qsession.close();
        qcon.close();
    }

    // sends the message to the queue
    public void send(String queueName, String message) throws Exception {

        // create a JNDI context to lookup JNDI objects (connection factory and queue)
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, Config.JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, Config.PROVIDER_URL);

        InitialContext ic = new InitialContext(env);
        init(ic, queueName);

        // send the message and close
        try {
            msg.setText(message);
            qsender.send(msg, DeliveryMode.PERSISTENT, 8, 0);
            System.out.println("The message was sent to the destination " +
                    qsender.getDestination().toString());
        } finally {
            close();
        }
    }

    public static void main(String[] args) throws Exception {
        // input arguments
        String msg = "Hello" ;
        String queueName = "jms/mdw-queue" ;

        // create the producer object and send the message
        JMSProducer producer = new JMSProducer();
        producer.send(queueName, msg);
    }

}
