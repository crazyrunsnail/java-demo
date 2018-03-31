package person.davino.basic.concurrency.daemon;

import java.util.concurrent.ThreadFactory;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 12/03/2018
 */
public class DaemonThreadFactory implements ThreadFactory{
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
