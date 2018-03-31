package person.davino.rx.notrx.model;

import sun.jvm.hotspot.utilities.BitMap;

/**
 * Description
 * <p>
 * Writed by davino
 * Created on 11/03/2018
 */
public class Cat implements Comparable<Cat>{
    BitMap bitMap;
    int cutness;

    @Override
    public int compareTo(Cat o) {
        return Integer.compare(cutness, o.cutness);
    }
}
