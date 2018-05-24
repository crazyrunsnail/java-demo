package person.davino.stream;

import java.util.Comparator;
import java.util.stream.Stream;

public class MinMaxDemo {
    public static void main(String[] args) {
        Stream.of("abc", "bac", "cab").min(Comparator.comparing(e -> e.length()))
                .ifPresent(System.out::println);
    }
}
