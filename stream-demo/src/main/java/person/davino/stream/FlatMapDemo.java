package person.davino.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * FlatMap限定返回的必须是流
 * <p>
 * Writed by davino
 * Created on 14/03/2018
 */
public class FlatMapDemo {

    public static void main(String[] args) {
        String[] strings = {"hello", "world"};

        Stream<String[]> ArrStream = Arrays.stream(strings).map(word -> word.split(""));

        List<Stream<String>> collect = ArrStream.map(ch -> Arrays.stream(ch)).collect(Collectors.toList());

        List<String> collect1 = Arrays.stream(strings)
                .map(word -> word.split(""))
                .flatMap(chs -> Arrays.stream(chs))
                .collect(Collectors.toList());

        collect1.stream().forEach(System.out::print);

        // List<String[]>
        List<String[]> collect2 = Arrays.stream(strings).map(word -> word.split("")).collect(Collectors.toList());
        Arrays.stream(collect2.get(1)).forEach(System.out::print);

        //Stream<List<Integer>>
        List<Integer> collect3 = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)).flatMap(numbers -> numbers.stream()).collect(Collectors.toList());

    }
}
