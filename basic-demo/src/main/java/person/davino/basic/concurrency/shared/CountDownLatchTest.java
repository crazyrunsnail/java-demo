package person.davino.basic.concurrency.shared;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Runnable a = () -> {
            try {
                Thread.sleep(100);
                System.out.println("线程a 执行成功！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }

        };

        Runnable b =() -> {
            try {
                Thread.sleep(100);
                System.out.println("线程b 执行成功！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }

        };

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(a);
        es.submit(b);
        es.shutdown();
        countDownLatch.await();
        System.out.println("线程到达");
    }
}
