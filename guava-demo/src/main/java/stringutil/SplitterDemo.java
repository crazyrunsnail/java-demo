package stringutil;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import java.util.Arrays;
import java.util.stream.Stream;

public class SplitterDemo {

    public static void main(String[] args) {
        // 使用Java内置的split, 只会忽略最后一个为空的字符
        String[] split = ",a,,   b,".split(",");
        Arrays.stream(split).forEach(x -> System.out.printf("\"%s\",", x));

        // 而guava的库却显得很灵活
        Iterable<String> split1 = Splitter.on(",")
                .trimResults()
                .omitEmptyStrings()
                .split(",a,,    b,");
        System.out.println();
        System.out.println("Guava splitter:");
        split1.forEach(x -> System.out.printf("\"%s\",", x));

        System.out.println();
        System.out.println("CharMather.anyof:");
        // 使用CharMatcher
        Splitter.on(CharMatcher.anyOf(";,."))
                .split("a,c.b;e").forEach(x -> System.out.printf("\"%s\",", x));
    }
}
