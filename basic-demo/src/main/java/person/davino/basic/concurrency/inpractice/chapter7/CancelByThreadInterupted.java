package person.davino.basic.concurrency.inpractice.chapter7;

import java.util.concurrent.TimeUnit;

public class CancelByThreadInterupted {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("doing work hard!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    System.out.println("Interuped!!!!");
                    break;
                }
            }
            System.out.println("out!!!");
        });
        t.start();

        TimeUnit.SECONDS.sleep(7);
        t.interrupt();
        TimeUnit.SECONDS.sleep(1);

    }
}
