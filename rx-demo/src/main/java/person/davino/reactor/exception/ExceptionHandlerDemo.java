package person.davino.reactor.exception;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Rx中处理异常有多种方式</p>
 * <ul>
 *     <li>捕获返回静态值</li>
 *     <li>捕获返回动态值</li>
 *     <li>捕获, 包装成业务异常</li>
 *     <li>捕获, 记录日记, 再抛出</li>
 *     <li>配合使用finally</li>
 * </ul>
 */
public class ExceptionHandlerDemo {

    public static void main(String[] args) {
        errorOnReturn();
        errorOnResume();
        // 异常并不会跳出当前的程序, 因为是另外一个线程中发生的
        errorOnMap();
        doOnError();
        // do finally会在最后输出
        doFinally();
    }

    private static void doFinally() {
        AtomicInteger num = new AtomicInteger(0);
        Flux.range(1, 6).map(i -> 10 / (i - 3)).doFinally(type -> {
            if (type == SignalType.CANCEL) {
                System.out.println("in do finally");
                num.getAndIncrement();
            }
        }).take(1).subscribe(System.out::println);
    }

    private static void doOnError() {
        Flux.range(1, 6).map(i -> 10 / (i-3)).doOnError(e -> {
            System.out.println("error happen!!!");
        }).onErrorResume(e -> {
            return Mono.just(1);
        }).subscribe(System.out::println);
    }

    /**
     * 抛出异常终止程序
     */
    private static void errorOnMap() {
        Flux.range(1, 6).map(i -> 10 / (i-3))
                .onErrorMap(e -> new RuntimeException(e)).subscribe(System.out::println, e -> e.printStackTrace());
    }

    /**
     * errorOnResume可以在代码中返回一个动态的值, 同样会中止当前的stream
     */
    private static void errorOnResume() {
        /*output:
            25
            100
            0
            complete!*/
        Flux.range(1, 6).map(i -> 10 / (i-3)).onErrorResume(e -> Mono.just(0)).map(i -> i * i)
                .subscribe(System.out::println, Throwable::printStackTrace, () -> {
                    System.out.println("complete!");
                });

    }

    /**
     * errorOnReturn会马上停止当前的stream
     */
    private static void errorOnReturn() {
        /*output:
        *   25
            100
            0
            complete!*/
        Flux.range(1, 6).map(i -> 10 / (i - 3)).onErrorReturn(0).map(i -> i * i)
                .subscribe(System.out::println, Throwable::printStackTrace, () -> {
                    System.out.println("complete!");
                });
    }
}
