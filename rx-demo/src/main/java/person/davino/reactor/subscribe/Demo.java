package person.davino.reactor.subscribe;

import reactor.core.publisher.Flux;

/**
 * subscribe();
 * <p>
 * subscribe(Consumer<? super T> consumer);
 * <p>
 * subscribe(Consumer<? super T> consumer,
 * Consumer<? super Throwable> errorConsumer);
 * <p>
 * subscribe(Consumer<? super T> consumer,
 * Consumer<? super Throwable> errorConsumer,
 * Runnable completeConsumer);
 * <p>
 * subscribe(Consumer<? super T> consumer,
 * Consumer<? super Throwable> errorConsumer,
 * Runnable completeConsumer,
 * Consumer<? super Subscription> subscriptionConsumer);
 * <p>
 * Writed by davino
 * Created on 14/03/2018
 */
public class Demo {
    public static void main(String[] args) {
        // 遍历所有的元素
//        Flux.just(1, 2, 3).subscribe(System.out::println);
//
//        Flux<Integer> ints = Flux.range(1, 4).map( i -> {
//            if (i <= 3) return i;
//            throw new RuntimeException("Got to 4");
//        });
//
//        ints.subscribe(System.out::println,
//                error -> System.err.println("Error: " + error ),
//                () -> System.out.println("Done")); // 报错不会运行到这里
//
//
//        Flux.range(1, 4).subscribe(System.out::println,
//                error -> System.out.println("Error: " + error),
//                () -> System.out.println("Done"));


        SampleSubcriber<Integer> ss = new SampleSubcriber<>();
        Flux<Integer> intss = Flux.range(1, 4);
//        intss.subscribe(e -> System.out.println("e:" + e),
//                error -> System.out.println("Error: " + error),
//                () -> System.out.println("Done"),
//                s -> ss.request(10));
        intss.subscribe(ss);


    }
}
