package person.davino.basic.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 07/03/2018
 */
public class DuplicatedOutputTest {
    int value = 0;
    List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
    List<Integer> list2 = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10));

    Runnable task1 = () -> {
        Iterator<Integer> iterator = list1.iterator();
        try {
            synchronized (this) {
                while (iterator.hasNext()) {
                    while (value != 0)
                        wait();
                    value = 1;
                    System.out.println(iterator.next());
                    iterator.remove();
                    notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    };

    Runnable task2 = () -> {
        try {
            Iterator<Integer> iterator = list2.iterator();
            synchronized (this) {
                while (iterator.hasNext()) {
                    while (value != 1) {
                        wait();
                    }
                    value = 0;
                    System.out.println(iterator.next());
                    iterator.remove();
                    notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    public static void main(String[] args) {
        ExecutorService services = Executors.newFixedThreadPool(2);
        DuplicatedOutputTest test = new DuplicatedOutputTest();
        services.execute(test.task1);
        services.execute(test.task2);
        services.shutdown();

    }


}
