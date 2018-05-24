package person.davino.stream.parallel;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiceRollsDemo {
    private final static int N = 1000000000;

    public static void main(String[] args) {
        double fraction = 1.0 / N;
        Map<Integer, Double> collect = IntStream.range(1, N)
                .parallel()
                .mapToObj(DiceRollsDemo::twoDiceThrows)
                .collect(Collectors.groupingBy(side -> side,
                        Collectors.summingDouble(n -> fraction)));

        collect.forEach((i, frac) -> {
            System.out.printf("Numer[%d] fraction is [%s]%n", i, frac);
        });
    }

    private static int twoDiceThrows(int i) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int first = random.nextInt(1, 7);
        int second = random.nextInt(1, 7);
        return first + second;
    }
}
