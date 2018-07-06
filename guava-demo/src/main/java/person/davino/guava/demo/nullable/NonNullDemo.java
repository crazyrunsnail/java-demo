package person.davino.guava.demo.nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

public class NonNullDemo {
    public static void main(String[] args) {


        // Optional
        Optional<Integer> optional = Optional.of(1111);

        System.out.println(optional.isPresent());

        if (optional.isPresent()) System.out.println(optional.get());

        optional = Optional.fromNullable(null);
        System.out.println(optional.or(1));

        // MoreObjects
        System.out.println(MoreObjects.firstNonNull("", "MoreObjects.firstNonNull"));
        System.out.println(MoreObjects.firstNonNull(null, "MoreObjects.firstNonNull"));


        // Strings
        Optional.fromNullable(Strings.emptyToNull(""));
        System.out.println();


    }
}
