package person.davino.rx.notrx.async.api;

import com.sun.jndi.toolkit.url.Uri;
import person.davino.rx.notrx.model.Cat;

import java.util.List;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 11/03/2018
 */
public interface ApiV2 {
    public interface CatsQueryCallback {

        void onCatListRecieved(List<Cat> cats);

        //错误错误
        void onErrors(Exception e);
    }

    void queryCats(String query, CatsQueryCallback callback);

    public interface StoreCallback{
        void onCatStored(Uri uri);
        void onStoreFail(Exception e);
    }
    

    void store(Cat cat, StoreCallback storeCallback);
}
