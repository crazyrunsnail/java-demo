package person.davino.basic.generic.bound;

import java.util.ArrayList;
import java.util.List;

/**
 * PECS原则
 * 生产 -> extends
 *  不能add，哪怕是 Object， 只能null，但是没有意义
 * 消费 -> super
 */
public class GenericsAndCovariance {

    public static void main(String[] args) {
        // Wildcards allow covariance:
        List<? extends Fruit> flist = new ArrayList<Apple>();
        // Compile Error: can't add any type of object:
        // flist.add(new Apgple());
        // flist.add(new Fruit());
        // flist.add(new Object());
        flist.add(null); // Legal but uninteresting
        // We know it returns at least Fruit:
        Fruit f = flist.get(0);
    }

}