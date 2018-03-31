package person.davino.rx.notrx.block.api;

import com.sun.jndi.toolkit.url.Uri;
import person.davino.rx.notrx.model.Cat;

import java.util.List;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 11/03/2018
 */
public interface Api {
    List<Cat> queryCats(String query);

    Uri store(Cat cat);

}
