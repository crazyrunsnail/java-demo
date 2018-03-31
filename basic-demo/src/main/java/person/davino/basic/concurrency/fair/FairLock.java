package person.davino.basic.concurrency.fair;

import person.davino.basic.concurrency.fair.QueueObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 12/03/2018
 */
public class FairLock {
    private boolean isLock = false;
    private Thread  lockingThread = null;
    private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();

    public void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();
        boolean isLockForThisThread = true;
        synchronized (this) {
            waitingThreads.add(queueObject);
        }
        while (isLockForThisThread) {
            synchronized (this) {
                isLockForThisThread = isLock || waitingThreads.get(0) != queueObject;
                if (!isLockForThisThread) {
                    isLock = true;
                    waitingThreads.remove(queueObject);
                    lockingThread = Thread.currentThread();
                    return;
                }

            }
            try {
                queueObject.doWait();
            } catch (InterruptedException e) {
                synchronized (this) {
                    waitingThreads.remove(queueObject);
                    throw  e;
                }
            }
        }
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        isLock = false;
        lockingThread = null;
        if (waitingThreads.size() > 0 ) {
            waitingThreads.get(0).doNotify();
        }
    }

}
