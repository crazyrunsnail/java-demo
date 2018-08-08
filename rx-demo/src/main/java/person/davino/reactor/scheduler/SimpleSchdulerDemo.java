package person.davino.reactor.scheduler;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SimpleSchdulerDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(1);
        System.out.println("Runing from Thread: " + Thread.currentThread().getName());
        Mono.fromCallable(SimpleSchdulerDemo::getStringAsyn)
                .subscribeOn(Schedulers.elastic())
                .subscribe(System.out::println, null, cdl::countDown);
        cdl.await(2, TimeUnit.SECONDS);
    }


    private static String getStringAsyn() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ite) {
            ite.printStackTrace();
        }
        System.out.println("Return String from Thread: " + Thread.currentThread().getName());
        return "String Asyn";
    }
}
