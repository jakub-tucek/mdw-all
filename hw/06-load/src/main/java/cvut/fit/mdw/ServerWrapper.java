package cvut.fit.mdw;

public class ServerWrapper {


    private final String serverName;

    private int usageBits = 0;

    private boolean health = false;

    public ServerWrapper(String serverName) {
        this.serverName = serverName;
    }

    public String getServerName() {
        return serverName;
    }

    public int getUsageBits() {
        return usageBits;
    }

    public boolean isHealth() {
        return health;
    }

    public synchronized void updateAccess() {
        ++this.usageBits;
        this.usageBits &= 0x00FF;
    }

    public synchronized void updateHealth(boolean health) {
        this.health = health;
    }
}
