package cvut.fit.mdw;

import cvut.fit.mdw.service.Currency;
import cvut.fit.mdw.service.CurrencyConverter;
import cvut.fit.mdw.service.CurrencyConverterImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements CurrencyConverter {

    private final CurrencyConverter currencyConverter;

    public Server() throws RemoteException {
        super();
        this.currencyConverter = new CurrencyConverterImpl();
    }

    public static void main(String[] args) {
        try {
            final Server server = new Server();

            LocateRegistry.createRegistry(1099);
            Naming.rebind("//0.0.0.0/currency", server);

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public double convert(Currency from, Currency to, double amount) throws RemoteException {
        return currencyConverter.convert(from, to, amount);
    }
}
