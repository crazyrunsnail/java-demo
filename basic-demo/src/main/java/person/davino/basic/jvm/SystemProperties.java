package person.davino.basic;

import java.util.Arrays;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 03/03/2018
 */
public class SystemProperties {

    public static void main(String[] args) {
        Arrays.stream(System.getProperty("java.class.path").split(":")).forEach(System.out::println);
        System.getProperty("sun.boot.class.path");

    }
}
