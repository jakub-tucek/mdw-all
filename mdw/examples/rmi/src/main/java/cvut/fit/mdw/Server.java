package cvut.fit.mdw;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements HelloInterface {


    protected Server() throws RemoteException {
        super();
    }

    @Override
    public String hello() throws RemoteException {
        return "Hello";
    }

    public static void main(String[] args) throws Exception {
        final Server server = new Server();

        LocateRegistry.createRegistry(1099);
        Naming.rebind("//0.0.0.0/hello", server);
    }
}
