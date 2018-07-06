package person.davino.guava.demo.precondition;

import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.*;

public class PreConditionDemo {

    public static void main(String[] args) {
        int i = -1;
        i = 1;
        checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
        checkNotNull(i);

        List<Integer> list = Arrays.asList(1, 2);
        checkState(list.iterator().hasNext());

        checkElementIndex(0, list.size()); // size exclusive
        checkPositionIndex(2, list.size()); // size inclusive
        checkPositionIndexes(0, 1, list.size());



    }
}
