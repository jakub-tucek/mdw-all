package cvut.fit.mdw;

import java.rmi.Naming;

public class Client {

    public static void main(String[] args) throws Exception{
        HelloInterface client = (HelloInterface) Naming.lookup("//localhost/hello");
        System.out.println(client.hello());
    }

}
