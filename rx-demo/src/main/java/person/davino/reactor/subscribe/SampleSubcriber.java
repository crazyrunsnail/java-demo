package person.davino.reactor.subscribe;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 14/03/2018
 */
public class SampleSubcriber<T> extends BaseSubscriber<T>{

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subcribed!");
        request(1);
    }

    @Override
    protected void hookOnNext(T value) {
        System.out.println(value);
        request(1);
    }
}
