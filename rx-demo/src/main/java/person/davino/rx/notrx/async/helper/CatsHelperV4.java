package person.davino.rx.notrx.async.helper;

import com.sun.jndi.toolkit.url.Uri;
import person.davino.rx.notrx.async.api.ApiWrapper;
import person.davino.rx.notrx.async.api.ApiWrapperV3;
import person.davino.rx.notrx.async.callback.Callback;
import person.davino.rx.notrx.async.job.AsyncJob;
import person.davino.rx.notrx.model.Cat;

import java.util.Collections;
import java.util.List;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 11/03/2018
 */
public class CatsHelperV4 {


    ApiWrapperV3 api;

    public AsyncJob<Uri> saveTheCutestCat(String query, Callback<Uri> cutestCatCallback) {
        AsyncJob<List<Cat>> catsListAsyncJob = api.queryCats(query);
        AsyncJob<Cat> cutestCatAsyncJob = new AsyncJob<Cat>() {
            @Override
            public void start(Callback<Cat> callback) {
                catsListAsyncJob.start(new Callback<List<Cat>>() {
                    @Override
                    public void onResult(List<Cat> cats) {
                        callback.onResult(findCutest(cats));
                    }

                    @Override
                    public void onException(Exception e) {
                        callback.onException(e);
                    }
                });
            }
        };

        AsyncJob<Uri> storeUriAsyncJob = new AsyncJob<Uri>() {
            @Override
            public void start(Callback<Uri> callback) {
                cutestCatAsyncJob.start(new Callback<Cat>() {
                    @Override
                    public void onResult(Cat cat) {
                        api.store(cat).start(new Callback<Uri>() {
                            @Override
                            public void onResult(Uri uri) {
                                callback.onResult(uri);
                            }

                            @Override
                            public void onException(Exception e) {
                                callback.onException(e);
                            }
                        });
                    }

                    @Override
                    public void onException(Exception e) {

                    }
                });
            }
        };

        return storeUriAsyncJob;


    }

    public Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
