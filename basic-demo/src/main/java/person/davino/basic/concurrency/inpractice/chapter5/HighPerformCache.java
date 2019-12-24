package person.davino.basic.concurrency.inpractice.chapter5;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * {@link HighPerformCache} 使用了组合模式 再配合 ConcurrentHashMap、futureTask来构建一个高性能缓存
 * FutureTask已经完成时, get直接返回
 * 最直接的不足是，如果线程太多会导致大量的future创建和销毁
 *
 *
 * @param <A> 入参类型
 * @param <V> 出参类型
 */
public class HighPerformCache<A, V> implements Computable<A, V> {

    private final ConcurrentHashMap<A, FutureTask<V>> cache =
            new ConcurrentHashMap<>();

    private Computable<A, V> c;

    @Override
    public V compute(A a) {
        while (true) {
            FutureTask<V> f = cache.get(a);
            if (f == null) {
                Callable<V> eval = () -> {
                    return c.compute(a);
                };
                FutureTask<V> ft = new FutureTask<>(eval);
                f = cache.putIfAbsent(a, ft);
                // 说明没有此任务
                if (f == null) {
                    f = ft;
                    f.run();
                }
            }

            try {
                return f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }


}


interface Computable<A, V> {
    V compute(A a);
}