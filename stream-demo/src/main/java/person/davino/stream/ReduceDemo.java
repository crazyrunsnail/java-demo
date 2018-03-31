package person.davino.stream;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 02/03/2018
 */
public class ReduceDemo {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("123", "456");
        List newList = new ArrayList();
        Optional<String> optional = list.stream().reduce((col, item) -> {
            return col.concat(" ").concat(item);
        });
        optional.ifPresent(System.out::println);
    }
}
