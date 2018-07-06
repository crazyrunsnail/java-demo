package person.davino.dp.compound.decorator;

import person.davino.dp.compound.Quackable;
import person.davino.dp.compound.observe.Observer;

/**
 * 这是一个装饰器, 本身继承了 Quackable 接口, 但依然持有 Quackable 对象
 */
public class QuackCounter implements Quackable{

    Quackable quackable;
    static int numberOfQuacks;

    public QuackCounter(Quackable quackable) {
        this.quackable = quackable;
    }

    @Override
    public void quack() {
        quackable.quack();
        numberOfQuacks++;
        notifyObservers();
    }

    public static int getQuacks() {
        return numberOfQuacks;
    }

    @Override
    public void registerObserve(Observer observer) {
        quackable.registerObserve(observer);
    }

    @Override
    public void notifyObservers() {

    }
}
