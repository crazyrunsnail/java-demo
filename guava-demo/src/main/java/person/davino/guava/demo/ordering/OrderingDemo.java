package person.davino.guava.demo.ordering;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.sun.istack.internal.Nullable;

import java.util.Comparator;
import java.util.List;

public class OrderingDemo {

    public static void main(String[] args) {
        String a = "abcfdfd12321312312321";
        String b = "1232131231";

        // 新生成一个Ordering
        Ordering<String> ordering = Ordering.from(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Ints.compare(o1.length(), o2.length());
            }
        });


        System.out.println(ordering.compare(a, b));
        System.out.println(Ordering.natural().compare(a, b));
        System.out.println(a.compareTo(b));


        Ordering<Foo> fooOrdering = Ordering.natural().nullsFirst().reverse().onResultOf(new Function<Foo, String>() {
            @Override
            public String apply(Foo foo) {
                return foo.sortedBy;
            }
        });
        List<Foo> foos = fooOrdering.greatestOf(Lists.newArrayList(new Foo(), new Foo()).iterator(), 1);
        foos.stream().forEach(System.out::println);


    }

    static class Foo {
        @Nullable
        String sortedBy;
        int notSortedBy;
    }
}
