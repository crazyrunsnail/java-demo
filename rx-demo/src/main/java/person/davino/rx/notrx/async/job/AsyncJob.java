package person.davino.rx.notrx.async.job;

import person.davino.rx.notrx.async.callback.Callback;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 11/03/2018
 */
public abstract class AsyncJob<T> {
    public abstract void start(Callback<T> callback);
}
