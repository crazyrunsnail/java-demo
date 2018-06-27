package person.davino.dp.proxy.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemote extends Remote{
    /**
     *
     * @return primitive or serialize
     * @throws RemoteException <em>MUST!!!</em>
     */
    public String sayHello() throws RemoteException;

}
