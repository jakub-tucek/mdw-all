package cz.fit.mdw.domain;

import cz.fit.mdw.Config;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public abstract class QueueWrapper {

    // connection factory
    private QueueConnectionFactory qconFactory;

    // connection to a queue
    private QueueConnection qcon;

    // session within a connection
    private QueueSession qsession;

    // queue where the message will be sent to
    private Queue queue;


    public QueueWrapper(String queueName) throws Exception {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, Config.JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, Config.PROVIDER_URL);

        InitialContext ctx = new InitialContext(env);

        qconFactory = (QueueConnectionFactory) ctx.lookup(Config.JMS_FACTORY);
        qcon = qconFactory.createQueueConnection();
        qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        queue = (Queue) ctx.lookup(queueName);

        initQueueSpecific();
    }

    public void close() throws Exception {
        closeSpecific();
        qsession.close();
        qcon.close();
    }

    protected abstract void closeSpecific() throws JMSException;
    protected abstract void initQueueSpecific() throws JMSException;

    public QueueConnectionFactory getQconFactory() {
        return qconFactory;
    }

    public QueueConnection getQcon() {
        return qcon;
    }

    public QueueSession getQsession() {
        return qsession;
    }

    public Queue getQueue() {
        return queue;
    }
}
