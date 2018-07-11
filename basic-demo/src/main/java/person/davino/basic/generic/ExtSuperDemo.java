package person.davino.basic.generic;

import java.util.ArrayList;
import java.util.List;

public class ExtSuperDemo{

    public static void main(String[] args) {
        List<? extends Fruit> fruits = new ArrayList<Apple>();

//        fruits.add(new Object());

        Fruit noLife = fruits.get(0);

        List<? super Fruit> f1 = new ArrayList<Fruit>();
//        List<? super Fruit> f2 = new ArrayList<Apple>();  // 报错, new Array<Fruit>();

        f1.add(new Apple()); // 向上转型成Fruit,
        f1.add(new RedApple()); // 向上转型成Fruit,  隐型可转.

//        NoLife o =  f1.get(0); // 报错, 因为只知道是下限是Fruit, 不知道是 NoLife还是Object, 需要强转.

    }
}

class NoLife{}

class Fruit extends NoLife{}

class Apple extends Fruit{}

class RedApple extends Apple{};
