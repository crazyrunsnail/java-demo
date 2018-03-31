package person.davino.rx.notrx.async.api;

import com.sun.jndi.toolkit.url.Uri;
import person.davino.rx.notrx.async.callback.Callback;
import person.davino.rx.notrx.model.Cat;

import java.util.List;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 11/03/2018
 */
public class ApiWrapper {
    ApiV2 api;

    public void queryCats(String query, Callback<List<Cat>> catsCallback) {
        api.queryCats(query, new ApiV2.CatsQueryCallback() {
            @Override
            public void onCatListRecieved(List<Cat> cats) {
                catsCallback.onResult(cats);
            }

            @Override
            public void onErrors(Exception e) {
                catsCallback.onException(e);
            }
        });
    }

    public void store(Cat cat, Callback<Uri> uriCallback) {
        api.store(cat, new ApiV2.StoreCallback() {
            @Override
            public void onCatStored(Uri uri) {
                uriCallback.onResult(uri);
            }

            @Override
            public void onStoreFail(Exception e) {
                uriCallback.onException(e);
            }
        });
    }

}
