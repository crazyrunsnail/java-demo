package person.davino.basic.concurrency.inpractice.chapter7;

import java.util.concurrent.*;

public class CancelByFuture {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executors = Executors.newFixedThreadPool(1);
        Future<?> received_interupted_singal = executors.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("do....");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    System.out.println("Received Interupted singal");
                    break;
                }
            }
            System.out.println("out!");
        });

        TimeUnit.SECONDS.sleep(1);

        try {
            received_interupted_singal.get(2, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            //System.out.println("Task timeout, cancel it by send interruped!");
            //received_interupted_singal.cancel(true);
        }

        // shutdown不会中断任务, 加入线程池报错
        executors.shutdown();

        // ture
        System.out.println(executors.isShutdown());
        // 有任务还在执行则为true
        System.out.println(executors.isTerminated());

    }
}
