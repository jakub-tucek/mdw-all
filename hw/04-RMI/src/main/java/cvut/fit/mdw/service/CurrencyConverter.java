package cvut.fit.mdw.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

@FunctionalInterface
public interface CurrencyConverter extends Remote  {

    double convert(Currency from, Currency to, double amount) throws RemoteException ;

}
