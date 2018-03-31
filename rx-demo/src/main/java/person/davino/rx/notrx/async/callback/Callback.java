package person.davino.rx.notrx.async.callback;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 11/03/2018
 */
public interface Callback<T> {
    void onResult(T t);
    void onException(Exception e);
}
