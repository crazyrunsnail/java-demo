package person.davino.rx.notrx.block.helper;

import com.sun.jndi.toolkit.url.Uri;
import person.davino.rx.notrx.block.api.Api;
import person.davino.rx.notrx.model.Cat;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 11/03/2018
 */
public class CatsHelper {
    Api api;

    public Uri saveTheCutestCat(String query){
        Uri saveUri = null;
        try {
            List<Cat> cats = api.queryCats(query);
            Cat cutest = findCutest(cats);
            saveUri = api.store(cutest);

        }catch (Exception e) {
            e.printStackTrace();
            try {
                saveUri = new Uri("123");
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
        }
        return saveUri;
    }

    public Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
