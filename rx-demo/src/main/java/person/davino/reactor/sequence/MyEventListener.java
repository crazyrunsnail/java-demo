package person.davino.reactor.sequence;

import java.util.List;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 20/03/2018
 */
public interface MyEventListener<T> {

    void onDataChunk(List<T> chunk);

    void processComplete();
}
