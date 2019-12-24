package person.davino.basic.concurrency.inpractice.chapter5;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁, 同步工具类的一种
 */
public class CountDownLatchSample {

    public long timeTasks( int nThreads, final Runnable task) {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Thread thread = new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
            thread.start();
        }
        long start = System.currentTimeMillis();
        startGate.countDown();
        return System.currentTimeMillis() - start;
    }
}
