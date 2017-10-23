package cvut.fit.service;
import java.util.Collection;
import java.util.Hashtable;

public class DB {

    private static DB instance = null;

    private Hashtable<Integer, Booking> ht = new Hashtable<>();
 
    public static DB getInstance() {
        if (instance == null)
            instance = new DB();
        return instance;
    }

}
