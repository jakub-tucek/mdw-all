package cvut.fit.mdw.config;

public class Config {

    // Defines the JNDI context factory.
    public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";

    // Defines the JMS context factory.
    public final static String JMS_FACTORY = "jms/mdw-cf";

    // URL
    public final static String PROVIDER_URL = "t3://localhost:7001";

    public final static String ALL_ORDERS_QUEUE = "jms/mdw-queue-all-orders";
    public final static String BOOKINGS_QUEUE = "jms/mdw-queue-bookings";
    public final static String NEW_TRIPS_QUEUE = "jms/mdw-queue-new-trips";

}
