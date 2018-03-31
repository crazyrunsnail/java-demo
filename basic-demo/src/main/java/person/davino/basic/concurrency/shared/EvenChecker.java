package person.davino.basic.concurrency.shared;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 14/03/2018
 */
public class EvenChecker implements Runnable{

    private IntGenerator generator;

    private final int id;

    public EvenChecker(IntGenerator generator, int indent) {
        this.generator = generator;
        this.id = indent;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 2 != 0){
                System.out.println(val + " not even!");
                generator.cancel();
            }
        }

    }

    public static void test(IntGenerator gp, int count) {
        System.out.println("Press ctrl-c to exit...");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++){
            exec.execute(new EvenChecker(gp, i));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator gp) {
        test(gp, 10);
    }

    public static void main(String[] args) {
//        EvenGenerator generator = new EvenGenerator();
        MutexEvenGenerator generator = new MutexEvenGenerator();
        test(generator);
    }

}
