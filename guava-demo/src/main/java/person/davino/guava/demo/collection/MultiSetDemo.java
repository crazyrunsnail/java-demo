package person.davino.guava.demo.collection;

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class MultiSetDemo {


    public static void main(String[] args) {
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("hello");
        multiset.add("world");
        multiset.add("hello");
        int helloCount = multiset.count("hello");
        int worldCount = multiset.count("world");
        System.out.printf("helloCount: %d, worldCound: %d \n", helloCount, worldCount);
        System.out.println("Multiset size: " + multiset.size());
        System.out.println("Multiset element size: " + multiset.elementSet().size());
        multiset.entrySet().forEach(System.out::println);
        multiset.setCount("java", 3);
        System.out.println("Multiset size after set count:" + multiset.size());
        multiset.entrySet().forEach(System.out::println);

    }

}
