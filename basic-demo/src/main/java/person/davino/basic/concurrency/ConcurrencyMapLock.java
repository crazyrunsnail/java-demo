package person.davino.basic.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author fengjy
 * @date 2020/9/17
 */
public class ConcurrencyMapLock {

    private static Map<Long, String> lock = new ConcurrentHashMap<>(32);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            getLook();
        });
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (Exception ignored) {

        }
        getLook();

        try {
            TimeUnit.SECONDS.sleep(12);
        } catch (Exception ignored) {

        }
        new Thread(() ->{
            System.out.println("关闭！");
            executorService.shutdownNow();
        }).start();;
    }

    public static void getLook() {
        String threadName = Thread.currentThread().getName();
        System.out.println("进入Lock：" + threadName);
        int tryLocktimes = 0;
        boolean isLocked = false;
        while (tryLocktimes < 3) {
            if (lock.put(1L, "TRUE") == null) {
                System.out.println("获取到：" + threadName);
                isLocked = true;
            }
            if (!isLocked) {
                tryLocktimes++;
                System.out.println(threadName + "> 第" + tryLocktimes + "次已获取到锁");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("订单支付回调，订单ID:1获取锁被中断.");
                    return;
                }
                continue;
            }
            System.out.println("获取到锁：" + threadName);
            break;
        }
        if (!isLocked) {
            System.out.println(threadName + "未获取到锁, 直接退出");
            return;
        }
        System.out.println("获取到锁进入业务: " + threadName);
        try {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception ignored) {

            }
        } finally {
            System.out.println("业务处理完成：" + threadName);
            if (isLocked) {
                System.out.println("释放：" + threadName);
                lock.remove(1L);
            }
        }
        System.out.println("退出：" + threadName);
    }
}
