package person.davino.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorDemo {

    private static Stream<String> stringStream = Stream.of("123", "abcd", "efdadfd");

    static List<Artist> artists = Arrays.asList(new Artist(Arrays.asList("a", "b", "c")),
            new Artist(Arrays.asList("aa", "bb", "cc")),
            new Artist(Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee", "fff")));

    public static void main(String[] args) {
        maxByCollector();

        average();

    }

    private static void average() {
        Double average = artists.stream().collect(Collectors.averagingInt(a -> a.getMembers().size()));
        System.out.println(average);
    }

    private static void maxByCollector() {
        Optional<String> max = stringStream
                .collect(Collectors.maxBy(Comparator.comparing(s -> s.length())));
        max.ifPresent(x -> System.out.println(x));
    }


}

class Artist {

    public Artist() {
        setMembers(Arrays.asList());
    }

    public Artist(List<String> members) {
        this.members = members;
    }

    private List<String> members;

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}


