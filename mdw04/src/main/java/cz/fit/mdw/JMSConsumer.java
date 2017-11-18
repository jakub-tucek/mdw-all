package cz.fit.mdw;

import cz.fit.mdw.domain.ConsumerQueueWrapper;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class JMSConsumer implements MessageListener {

    private ConsumerQueueWrapper queueWrapper;

    // callback when the message exist in the queue
    public void onMessage(Message msg) {
        try {
            String msgText;
            if (msg instanceof TextMessage) {
                msgText = ((TextMessage) msg).getText();
            } else {
                msgText = msg.toString();
            }
            System.out.println("Confirmation Received: " + msgText);
        } catch (JMSException jmse) {
            System.err.println("An exception occurred: " + jmse.getMessage());
        }
    }


    // start receiving messages from the queue
    public void receive(String queueName) throws Exception {
        this.queueWrapper = new ConsumerQueueWrapper(this, queueName);

        System.out.println("Connected to " + this.queueWrapper.toString() + ", receiving messages...");
        try {
            synchronized (this) {
                while (true) {
                    this.wait();
                }
            }
        } finally {
            queueWrapper.close();
            System.out.println("Finished.");
        }
    }

    public static void main(String[] args) throws Exception {
        // input arguments
        String confirmationQueue = "jms/mdw-queue";

        // create the producer object and receive the message
        JMSConsumer consumer = new JMSConsumer();
        consumer.receive(confirmationQueue);
    }
}
