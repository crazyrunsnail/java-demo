package person.davino.basic.concurrency.fair;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 12/03/2018
 */
public class FairTest {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        FairLock lock = new FairLock();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            Runnable r = () -> {
                System.out.println("Thread{" + j + "} run....");
                try {
                    lock.lock();
                    System.out.println("Thread{" + j + "} get the lock;");
                    TimeUnit.SECONDS.sleep(1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("Thread{" + j + "}  unlock;");
                    lock.unlock();
                }
            };
            exec.execute(r);
        }
        exec.shutdown();
    }




}
