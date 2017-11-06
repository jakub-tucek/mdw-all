package cvut.fit.mdw;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloInterface extends Remote {

    String hello() throws RemoteException;
}
