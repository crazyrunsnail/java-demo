package person.davino.basic.concurrency.inpractice.chapter7;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BrokenPrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;
    private  volatile boolean cancelled = false;
    private volatile  boolean needed = true;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        try {
            while (!cancelled) {
                p = p.nextProbablePrime();
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(String.format("Put item to queue on %s",
                        Thread.currentThread().getName()));
                // 这里可能永远阻塞， 就不会检查到cancelled, 导致永远退不出
                queue.put(p);
            }
            System.out.println("Cancelled! Canceled is " + cancelled);
        } catch (InterruptedException consumed) {}
    }

    void cancel() {
        this.cancelled = true;
    }

    boolean needPrime() {
        return needed;
    }

    void stopNeed() {
        this.needed = false;
    }


    public static void main(String[] args) {

        BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<>(10);
        BrokenPrimeProducer primeProducer = new BrokenPrimeProducer(queue);
        primeProducer.start();

        final Thread thread = Thread.currentThread();

        System.out.println("Start a Interruped thread after 10 seconds!");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("Stop the need in " + Thread.currentThread().getName());
                    primeProducer.stopNeed();
                } catch (InterruptedException e) {
                    System.out.println("Interrupte!");
                }
            }
        }).start();


        try {
            while (primeProducer.needed) { // 当另外一个线程将need置为false时, 不会再消费
                // 可能永远阻塞
                BigInteger take = queue.take();
                System.out.println(String.format("Token from queue in %s",
                        Thread.currentThread().getName()));
                TimeUnit.MILLISECONDS.sleep(500);
                // dosometing to taken
            }
        } catch (InterruptedException e) {
            System.out.println("Get a Interruped signal.");
        } finally {
            System.out.println("Cancel!");
            // 虽然这个时候把 cancelled 置为了 false, 但消费者中还是阻塞, 不会检查 cancelled!
            primeProducer.cancel();
        }
    }


}
