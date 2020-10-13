package person.davino.stream;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author fengjy
 * @date 2020/9/22
 */
public class CompletableFutureTests {

    private final static ExecutorService services = Executors.newCachedThreadPool();

    public static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception ex) {
                System.out.println(ex);
            }
            System.out.println(Thread.currentThread().getName() + ": getName;");
            return "name";
        }, services);
    }

    public static CompletableFuture<User> getUserByName(String name) {
        System.out.println(Thread.currentThread().getName() + ": getUser();");
        return CompletableFuture.supplyAsync(() -> new User(name), services);
    }

    public static void main(String[] args) {
        User user = getName().thenCompose(name -> getUserByName(name)).join();
        System.out.println(Thread.currentThread().getName() + ":::" + user);
        services.shutdown();
    }

    private static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("User{");
            sb.append("name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
