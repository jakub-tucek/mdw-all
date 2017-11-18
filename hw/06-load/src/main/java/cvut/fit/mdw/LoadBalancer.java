package cvut.fit.mdw;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class LoadBalancer implements Runnable {


    private List<ServerWrapper> servers;

    private boolean runFlag = true;

    private boolean initialized = false;

    public LoadBalancer() {
        servers = Arrays.asList(
                new ServerWrapper("http://147.32.233.18:8888/MI-MDW-LastMinute1/list"),
                new ServerWrapper("http://147.32.233.18:8888/MI-MDW-LastMinute2/list"),
                new ServerWrapper("http://147.32.233.18:8888/MI-MDW-LastMinute3/list")
        );
    }

    private void checkStatuses() {
        servers.forEach(this::checkStatus);
    }

    public Optional<ServerWrapper> getUrl() {
        if (!initialized) {
            this.checkStatuses();
            initialized = true;
        }

        Optional<ServerWrapper> serverWrapper = servers.stream()
                .filter(ServerWrapper::isHealth)
                .sorted(Comparator.comparing(ServerWrapper::getUsageBits))
                .findFirst();

        serverWrapper.ifPresent(ServerWrapper::updateAccess);

        return serverWrapper;
    }

    private void checkStatus(ServerWrapper serverWrapper) {
        String url = "http://fit.cvut.cz";

        boolean result = false;

        try {
            HttpURLConnection connection = (HttpURLConnection) (new URL(serverWrapper.getServerName())).openConnection();
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();

            System.out.println(serverWrapper.getServerName() + " status is:" + code);

            if (code == 200) {
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error when checking status: " + e.getMessage());
        }

        serverWrapper.updateHealth(result);
    }

    @Override
    public void run() {

        try {

            while (runFlag) {
                System.out.println("Checking server status");

                checkStatuses();
                initialized = true;

                Thread.sleep(10000);
            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
            System.out.println("Error occured");
        }
    }

    public void stop() {
        this.runFlag = false;
    }
}
