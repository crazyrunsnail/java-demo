package person.davino.basic.concurrency.daemon;

import java.util.concurrent.TimeUnit;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 12/03/2018
 */
public class SimpleDaemon implements Runnable{
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println(Thread.currentThread() + " " + this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        for(int i = 0 ; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemon());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons start...");
        TimeUnit.MILLISECONDS.sleep(175);

    }
}
