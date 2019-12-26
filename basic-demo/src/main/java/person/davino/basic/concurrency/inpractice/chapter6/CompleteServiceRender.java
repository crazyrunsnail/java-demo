package person.davino.basic.concurrency.inpractice.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 重写TaskFuture, 在任务完成后放入队列当中
 */
public class CompleteServiceRender {
    private final ExecutorService executorService = Executors.newCachedThreadPool();


    public static void main(String[] args) throws InterruptedException {

        CompleteServiceRender completeServiceRender = new CompleteServiceRender();
        List<String> imageInfos = new ArrayList<>();
        imageInfos.add("123");
        CompleteService<String> completeService =
                new CompleteService<>(completeServiceRender.executorService);

        for (String imageInfo : imageInfos) {
            completeService.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    return "123";
                }
            });
        }


        for (int t = 0, n = imageInfos.size(); t < n; t++) {
            try {
                Future<String> f = completeService.take();
                String s = f.get();
                // do something
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    private final static class CompleteService<V> {
        private final BlockingQueue<FutureTask<V>> blockingQueue =
                new ArrayBlockingQueue<FutureTask<V>>(10);

        final ExecutorService executorService;

        public CompleteService(ExecutorService executorService) {
            this.executorService = executorService;
        }

        public Future<?> submit(Callable callable) {
            Future<?> submit = executorService.submit(new QueueFuture<V>(callable));
            return submit;
        }

        public FutureTask<V> take() throws InterruptedException {
            return blockingQueue.take();
        }

        private final class QueueFuture<V> extends FutureTask {

            public QueueFuture(Callable<V> callable) {
                super(callable);
            }

            public QueueFuture(Runnable runnable, V result) {
                super(runnable, result);
            }

            @Override
            protected void done() {
                blockingQueue.add(this);
            }
        }
    }




}
