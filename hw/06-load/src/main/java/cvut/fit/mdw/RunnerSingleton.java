package cvut.fit.mdw;

public class RunnerSingleton {

    private static LoadBalancer loadBalancer;


    public static LoadBalancer getLoadBalancer() {
        if (loadBalancer == null) {
            loadBalancer = new LoadBalancer();
            loadBalancer.run();
        }
        return loadBalancer;
    }
}
