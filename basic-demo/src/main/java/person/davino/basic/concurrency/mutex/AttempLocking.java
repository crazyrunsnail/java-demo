package person.davino.basic.concurrency.mutex;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 14/03/2018
 */
public class AttempLocking {
    private ReentrantLock lock = new ReentrantLock();
    public void untimed() {
        // reentratlock的另一种用法
        // 不会blocking, 立即返回true or false
        boolean captured = lock.tryLock();
        try {
            if (captured) {
                System.out.println("tryLock()" + captured);
            }
        }finally {
            if (captured)
                lock.unlock();
        }
    }

    public void timed() {
        boolean caputured = false;
        try {
            // 尝试等待两秒, 如果还是没有获得锁则返回 false
             caputured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS):" + caputured);
        }finally {
            if (caputured)
                lock.unlock();
        }
    }

    public static void main(String[] args) {
        final AttempLocking al = new AttempLocking();
        al.untimed();
        al.timed();
        new Thread(){
            {setDaemon(true);}
            public void run(){
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();

        Thread.yield();
        al.untimed();
        al.timed();

    }
}
