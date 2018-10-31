package person.davino.stream;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PeekDemo {
    public static void main(String[] args) {
        StringBuilder[] strings = {new StringBuilder("a"),
                new StringBuilder("b"),
                new StringBuilder("c"), new StringBuilder("b")};
        List<StringBuilder> as = Arrays.stream(strings).filter(s ->
             s.toString().equals("a")
        ).peek(s -> s.append("]")).collect(Collectors.toList());
        as.forEach(System.out::println);
    }
}
