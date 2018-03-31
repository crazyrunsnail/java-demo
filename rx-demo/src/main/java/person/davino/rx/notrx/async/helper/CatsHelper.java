package person.davino.rx.notrx.async.helper;

import com.sun.jndi.toolkit.url.Uri;
import person.davino.rx.notrx.async.api.Api;
import person.davino.rx.notrx.model.Cat;

import java.util.Collections;
import java.util.List;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 11/03/2018
 */
public class CatsHelper {
    public interface CutestCatCallback{
        void onCutestCatSaved(Uri uri);
        void onQueryFailed(Exception e);
    }

    Api api;

    public void saveTheCutestCat(String query, CutestCatCallback cutestCatCallback) {
        api.queryCats(query, new Api.CatsQueryCallback() {
            @Override
            public void onCatListRecieved(List<Cat> cats) {
                Cat cutest = findCutest(cats);
                Uri uri = api.store(cutest);
                cutestCatCallback.onCutestCatSaved(uri);
            }

            @Override
            public void onErrors(Exception e) {
                cutestCatCallback.onQueryFailed(e);
            }
        });
    }

    public Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
