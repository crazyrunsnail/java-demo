package person.davino.basic.concurrency.shared;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 14/03/2018
 */
public abstract class IntGenerator {

    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
