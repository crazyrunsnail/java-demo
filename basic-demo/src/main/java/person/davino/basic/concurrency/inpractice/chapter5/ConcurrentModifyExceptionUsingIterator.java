package person.davino.basic.concurrency.inpractice.chapter5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * for 循环使用的外部 iterator, 需要手动同步
 *
 *
 */
public class ConcurrentModifyExceptionUsingIterator {


    private List<Long> widget =
            Collections.synchronizedList(new ArrayList<>());

    public void mayException() {
        // 没有同步可以会抛出 ConcurrentModifyException
        // 因为是使用了iterator，需要客户端同步
        for (Long aLong : widget) {
            // dosomething...
        }
    }


}
