package person.davino.reactor.publisher;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 * Flux 表示 1..N
 * <p>
 * Writed by davino
 * Created on 14/03/2018
 */
public class FluxDemo {
    public static void main(String[] args) {
        Flux.just(1, 2, 3);
        List<Integer> iterable = Arrays.asList(1, 2, 3);
        Flux.fromIterable(iterable);
        Flux.fromStream(iterable.stream());
        Flux.fromArray(new String[10]);

        Flux.range(1, 10);
    }
}
