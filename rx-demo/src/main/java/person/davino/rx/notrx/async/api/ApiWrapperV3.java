package person.davino.rx.notrx.async.api;

import com.sun.jndi.toolkit.url.Uri;
import person.davino.rx.notrx.async.callback.Callback;
import person.davino.rx.notrx.async.job.AsyncJob;
import person.davino.rx.notrx.model.Cat;

import java.util.List;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 11/03/2018
 */
public class ApiWrapperV3 {
    ApiV2 api;

    public AsyncJob<List<Cat>> queryCats(String query) {
        return new AsyncJob<List<Cat>>() {
            @Override
            public void start(Callback<List<Cat>> callback) {
                api.queryCats(query, new ApiV2.CatsQueryCallback() {
                    @Override
                    public void onCatListRecieved(List<Cat> cats) {
                        callback.onResult(cats);
                    }

                    @Override
                    public void onErrors(Exception e) {
                        callback.onException(e);
                    }
                });
            }
        };
    }

    public AsyncJob<Uri> store(Cat cat) {
        return new AsyncJob<Uri>() {
            @Override
            public void start(Callback<Uri> callback) {
                api.store(cat, new ApiV2.StoreCallback() {
                    @Override
                    public void onCatStored(Uri uri) {
                        callback.onResult(uri);
                    }

                    @Override
                    public void onStoreFail(Exception e) {
                        callback.onException(e);
                    }
                });
            }
        };
    }

}
