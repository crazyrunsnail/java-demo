package person.davino.reactor.sequence;

import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 15/03/2018
 */
public class GenerateDemo {
    public static void main(String[] args) {
        /*Flux<String> flux = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) sink.complete();
                    return state + 1;
                }
        );*/

        // Generate 生成一个sequence
        Flux<String> flux1 = Flux.generate(
                AtomicLong::new, //初始值
                (state, sink) -> { // 一个BiFunction, 返后state..
                    long i = state.getAndIncrement();
                    sink.next("3 x" + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;
                }, (state) -> System.out.println("state: " + state)
        );

        flux1.subscribe(System.out::println, error -> System.out.println(error));
    }
}
