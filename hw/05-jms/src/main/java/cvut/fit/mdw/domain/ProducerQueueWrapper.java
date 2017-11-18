package cvut.fit.mdw.domain;

import javax.jms.JMSException;
import javax.jms.QueueSender;

public class ProducerQueueWrapper extends QueueWrapper {

    // queue sender that sends a message to the queue
    private QueueSender qsender;

    public ProducerQueueWrapper(String queueName) throws Exception {
        super(queueName);
    }

    protected void closeSpecific() throws JMSException {
        qsender.close();
    }

    protected void initQueueSpecific() throws JMSException {
        qsender = getQsession().createSender(getQueue());
    }

    public QueueSender getQsender() {
        return qsender;
    }
}
