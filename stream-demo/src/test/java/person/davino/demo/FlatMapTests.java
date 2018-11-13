package person.davino.demo;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.*;

public class FlatMapTests {
    @Test
    public void optional() {
        Optional<String> optional = Optional.of("test");
        assertEquals(Optional.of("TEST"), optional.map(String::toUpperCase));
    }

    @Test
    public void optional2() {
        assertEquals(Optional.of(Optional.of("STRING")),
                Optional.of("string").map(s -> Optional.of("STRING")));
    }

    @Test
    public void optional3() {
        assertEquals(Optional.of("STRING"), Optional.of("string").flatMap(s -> Optional.of("STRING")));
    }

    @Test
    public void stream() {
        List<String> myList = Stream.of("a", "b").map(String::toUpperCase).collect(Collectors.toList());
        assertEquals(Arrays.asList("A", "B"), myList);
    }

    @Test
    public void stream2() {
        List<List<String>> list = Arrays.asList(
                Arrays.asList("a"),
                Arrays.asList("b")
        );
        System.out.println(list);

        Stream<List<String>> stream = list.stream();
        System.out.println(list.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
    }

}
