package cz.fit.mdw.domain;

import sun.misc.Queue;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.QueueReceiver;

public class ConsumerQueueWrapper extends QueueWrapper {

    // queue receiver that receives a message to the queue
    private QueueReceiver qreceiver;

    private MessageListener messageListener;

    public ConsumerQueueWrapper(MessageListener messageListener, String queueName) throws Exception {
        super(queueName);
        this.qreceiver.setMessageListener(messageListener);
    }


    protected void closeSpecific() throws JMSException {
        qreceiver.close();
    }

    protected void initQueueSpecific() throws JMSException {
        qreceiver = getQsession().createReceiver(getQueue());
        qreceiver.setMessageListener(messageListener);
        getQcon().start();
    }
}
