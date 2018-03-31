package person.davino.rx.notrx.async.helper;

import com.sun.jndi.toolkit.url.Uri;
import person.davino.rx.notrx.async.api.ApiV2;
import person.davino.rx.notrx.model.Cat;

import java.util.Collections;
import java.util.List;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 11/03/2018
 */
public class CatsHelperV2 {
    public interface CutestCatCallback{
        void onCutestCatSaved(Uri uri);
        void onError(Exception e);
    }

    ApiV2 api;

    public void saveTheCutestCat(String query, CutestCatCallback cutestCatCallback) {
        api.queryCats(query, new ApiV2.CatsQueryCallback() {
            @Override
            public void onCatListRecieved(List<Cat> cats) {
                Cat cutest = findCutest(cats);
                api.store(cutest, new ApiV2.StoreCallback() {
                    @Override
                    public void onCatStored(Uri uri) {
                        cutestCatCallback.onCutestCatSaved(uri);
                    }

                    @Override
                    public void onStoreFail(Exception e) {
                        cutestCatCallback.onError(e);
                    }
                });
            }

            @Override
            public void onErrors(Exception e) {
                cutestCatCallback.onError(e);
            }
        });
    }

    public Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
