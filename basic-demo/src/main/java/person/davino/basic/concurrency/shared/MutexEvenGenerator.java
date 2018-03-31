package person.davino.basic.concurrency.shared;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 14/03/2018
 */
public class MutexEvenGenerator extends IntGenerator{
    private int currentValue = 0;

    private ReentrantLock lock = new ReentrantLock();


    @Override
    public int next() {
        lock.lock();
        // lock之后马上使用try, 这样可以在finally里unlock, 保证线程不会陷入死锁或者是饿死
        try {
            currentValue += 2;
            return currentValue;
        }finally {
            lock.unlock();
        }

    }
}
