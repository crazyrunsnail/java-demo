package person.davino.reactor.publisher;

import reactor.core.publisher.Mono;

/**
 * Mono 表示0或1
 * 类似通配符的?
 * <p>
 * Writed by davino
 * Created on 14/03/2018
 */
public class MonoDemo {
    public static void main(String[] args) {
        Mono mono1 = Mono.empty();
        Mono mono2 = Mono.just(1);
    }
}
