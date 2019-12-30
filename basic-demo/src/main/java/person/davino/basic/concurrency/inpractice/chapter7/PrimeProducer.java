package person.davino.basic.concurrency.inpractice.chapter7;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 显性地使用 cancel 字段来有可能让设置cancel字段的代码永远不会运行
 */
public class PrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;
    private volatile  boolean needed = true;

    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                p = p.nextProbablePrime();
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(String.format("Put item to queue on %s",
                        Thread.currentThread().getName()));
                queue.put(p);
            }
        } catch (InterruptedException consumed) {
            System.out.println("Thread had been iterrupted! ");
        }
    }

    void cancel() {
        interrupt();
    }

    boolean needPrime() {
        return needed;
    }

    void stopNeed() {
        this.needed = false;
    }


    public static void main(String[] args) {

        BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<>(10);
        PrimeProducer brokenPrimeProducer = new PrimeProducer(queue);
        brokenPrimeProducer.start();

        final Thread thread = Thread.currentThread();

        System.out.println("Start a Interruped thread after 10 seconds!");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("Stop the need in " + Thread.currentThread().getName());
                    brokenPrimeProducer.stopNeed();
                } catch (InterruptedException e) {
                    System.out.println("Interrupte!");
                }
            }
        }).start();


        try {
            while (brokenPrimeProducer.needed) {
                // 可能永远阻塞
                BigInteger take = queue.take();
                System.out.println(String.format("Token from queue in %s",
                        Thread.currentThread().getName()));
                TimeUnit.MILLISECONDS.sleep(500);
                // dosometing to taken
            }
        } catch (InterruptedException e) {
            System.out.println("Get a Interruped signal.");
        }finally{
            System.out.println("Cancel!");
            brokenPrimeProducer.cancel();
        }



    }
}
