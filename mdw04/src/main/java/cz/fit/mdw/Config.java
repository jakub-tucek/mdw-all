package cz.fit.mdw;

public class Config {

    // Defines the JNDI context factory.
    public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";

    // Defines the JMS context factory.
    public final static String JMS_FACTORY = "jms/mdw-cf";

    // URL
    public final static String PROVIDER_URL = "t3://localhost:7001";

    public final static String BOOKING_QUEUE = "jms/mdw-queue-booking";

    public final static String CONFIRMATION_QUEUE = "jms/mdw-queue-confirmation";

}
