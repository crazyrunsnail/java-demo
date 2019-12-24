package person.davino.basic.concurrency.inpractice;

/**
 * 实际上想测试编译器优化, 但在1.8_162下不成功
 *
 * answer有可能输出0
 */
public class DisruptOrderTest {
    static boolean answerReady = false;
    static int answer = 0;
    static Thread t1 = new Thread(() -> {
        answer = 42;
        answerReady = true;
    });

    static Thread t2 = new Thread(() -> {
        if (answerReady)
            System.out.println("The meaning of life is: " + answer);
        else
            System.out.println("I don't know the answer.");
    });

    public static void main(String[] args) throws InterruptedException {
        test();
    }

    static void test() throws InterruptedException {
        t1.start(); t2.start();
        t1.join(); t2.join();
    }
}
