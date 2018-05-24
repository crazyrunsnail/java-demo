package person.davino.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CustomCollectorDemo {

    public static void main(String[] args) {
        String name = Stream.of("abc", "cde", "fgh").reduce(new StringCombiner("[", ",", "]"),
                StringCombiner::add, StringCombiner::merge).toString();
        System.out.println(name);
    }

}

class StringCombiner {
    public StringBuilder builder;
    private String prefix;
    private String delim;
    private String suffix;

    public StringCombiner(String prefix, String delim, String suffix) {
        this.prefix = prefix;
        this.delim = delim;
        this.suffix = suffix;
        this.builder = new StringBuilder();
    }

    private boolean areStart() {
        return this.builder.length() == 0;
    }

    public StringCombiner add(String element) {
        System.out.println("adding: " + element);
        if (areStart()) {
            this.builder.append(prefix);
        } else {
            this.builder.append(delim);
        }
        this.builder.append(element);
        return this;
    }

    public StringCombiner merge(StringCombiner other) {
        System.out.println("merge....");
        this.builder.append(other.builder);
        return this;
    }

    public String finish() {
        System.out.println("in finish...");
        this.builder.append(suffix);
        return builder.toString();
    }

    @Override
    public String toString() {
        return this.builder.toString();
    }
}

class StringCollector implements Collector<String, StringCombiner, String> {

    private String prefix;
    private String delim;
    private String suffix;

    @Override
    public Supplier<StringCombiner> supplier() {
        return () -> new StringCombiner(prefix, delim, suffix);
    }

    @Override
    public BiConsumer<StringCombiner, String> accumulator() {
        return StringCombiner::add;
    }

    @Override
    public BinaryOperator<StringCombiner> combiner() {
        return StringCombiner::merge;
    }

    @Override
    public Function<StringCombiner, String> finisher() {
        return StringCombiner::finish;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return new HashSet<Characteristics>(Arrays.asList(Characteristics.IDENTITY_FINISH));
    }
}
