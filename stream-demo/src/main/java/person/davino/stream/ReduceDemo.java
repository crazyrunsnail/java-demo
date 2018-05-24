package person.davino.stream;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 02/03/2018
 */
public class ReduceDemo {

    public static void main(String[] args) {
        /*List<String> list = Arrays.asList("123", "456");
        List newList = new ArrayList();
        Optional<String> optional = list.stream().reduce((col, item) -> {
            return col.concat(" ").concat(item);
        });
        optional.ifPresent(System.out::println);*/

        reduce();



    }

    public static void reduce() {
        int reduce = IntStream.range(1, 3).map(x -> x * x).
                reduce(5, (acc, ele) -> acc + ele);

        // 并行时, 初始值也会被加两次, 如果直接上面一样写, 计算的结果将会有出入, 与并行的线程数有关系.
        int reduce1 = 5 + IntStream.range(1, 3).map(x -> x * x).
                reduce(0, (acc, ele) -> acc + ele);
        System.out.println(reduce);
        System.out.println(reduce);
    }
}
