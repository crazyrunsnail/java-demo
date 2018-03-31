package person.davino.basic.concurrency.shared;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 14/03/2018
 */
public class EvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    // synchronized 不算入signature, 但throw是算入的
    @Override
    public synchronized int next() {
        ++currentEvenValue; // 不是原子性操作
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }
}
