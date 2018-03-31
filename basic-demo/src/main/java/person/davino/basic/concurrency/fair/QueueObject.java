package person.davino.basic.concurrency.fair;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 12/03/2018
 */
public class QueueObject {
    private boolean isNotified = false;
    public synchronized void doWait() throws InterruptedException {
        while (!isNotified)
            wait();

        isNotified = false;
    }

    public synchronized void doNotify() {
        this.isNotified = true;
        this.notify();
    }

    public boolean equals(Object o) {
        return this == o;
    }


}
