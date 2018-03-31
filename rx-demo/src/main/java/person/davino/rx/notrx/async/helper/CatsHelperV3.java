package person.davino.rx.notrx.async.helper;

import com.sun.jndi.toolkit.url.Uri;
import person.davino.rx.notrx.async.api.ApiV2;
import person.davino.rx.notrx.async.api.ApiWrapper;
import person.davino.rx.notrx.async.callback.Callback;
import person.davino.rx.notrx.model.Cat;

import java.util.Collections;
import java.util.List;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 11/03/2018
 */
public class CatsHelperV3 {


    ApiWrapper api;

    public void saveTheCutestCat(String query, Callback<Uri> cutestCatCallback) {
        api.queryCats(query, new Callback<List<Cat>>() {
            @Override
            public void onResult(List<Cat> cats) {
                Cat cutest = findCutest(cats);
                api.store(cutest, cutestCatCallback);
            }

            @Override
            public void onException(Exception e) {
                cutestCatCallback.onException(e);
            }
        });
    }

    public Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
