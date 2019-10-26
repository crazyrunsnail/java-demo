package person.davino.reactor.scheduler;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleSchdulerDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(1);
        System.out.println("Runing from Thread: " + Thread.currentThread().getName());
        Mono.fromCallable(SimpleSchdulerDemo::getStringAsyn)
                .subscribeOn(Schedulers.immediate())    // 使用的是当前线程 main
//                .subscribeOn(Schedulers.single())     // 使用single-1线程
//                .subscribeOn(Schedulers.elastic())  // 使用elastic-2线程
//                .subscribeOn(Schedulers.parallel()) // 多线程, 与CPU核数相关
//                .subscribeOn(Schedulers.fromExecutor(Executors.newSingleThreadExecutor())) // 允许使用相对应的线程池
                .subscribe(System.out::println, null, cdl::countDown);
        cdl.await(2, TimeUnit.SECONDS);
    }


    private static String getStringAsyn() {
        try {
            System.out.println("Current thread name is :" + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ite) {
            ite.printStackTrace();
        }
        System.out.println("Return String from Thread: " + Thread.currentThread().getName());
        return "String Asyn";
    }
}
