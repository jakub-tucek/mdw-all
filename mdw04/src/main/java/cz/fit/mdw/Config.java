package cz.fit.mdw;

public class Config {

    // Defines the JNDI context factory.
    public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";

    // Defines the JMS context factory.
    public final static String JMS_FACTORY = "jms/mdw-cf";

    // URL
    public final static String PROVIDER_URL = "t3://localhost:7001";

}
