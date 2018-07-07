package person.davino.basic.jvm;

import java.util.Arrays;
import java.util.Map;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 03/03/2018
 */
public class SystemProperties {

    public static void main(String[] args) {
        // 对应 AppClassLoader
        Arrays.stream(System.getProperty("java.class.path").split(":")).forEach(System.out::println);
        // BootstrapClassLoader
        System.out.println("=== sun.boot.class.path ===");
        System.getProperty("sun.boot.class.path");

        // env Map
        System.out.println("=== env ===");
        Map<String, String> env = System.getenv();
        for (String key: env.keySet()) {
            System.out.println(key + ":" + env.get(key));
        }

        // ExtClassLoader
        System.out.println("===java.ext.dirs===");
        System.out.println(System.getProperty("java.ext.dirs"));

    }
}
