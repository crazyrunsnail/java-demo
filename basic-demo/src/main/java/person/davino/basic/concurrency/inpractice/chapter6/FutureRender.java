package person.davino.basic.concurrency.inpractice.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 将任务分为两部分，一分部去抓图片一部分去处理图片
 * 
 */
public class FutureRender {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    void renderPage(CharSequence source) {
        final List<String> imageInfos = new ArrayList<>();
        Callable<List<String>> task;
        task = () -> {
            List<String> result = new ArrayList<>();
            for (String image: imageInfos) {
                result.add("image");

            }
            return result;
        };

        Future<List<String>> future = executorService.submit(task);

        try {
            List<String> strings = future.get();
            // 处理
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
