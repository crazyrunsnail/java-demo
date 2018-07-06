package person.davino.dp.compound.observe;

import person.davino.dp.compound.Quackable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 实际上是一个helper
 */
public class Observable implements QuackObservable{
    List observers = new ArrayList();

    QuackObservable duck;

    public Observable(QuackObservable duck) {
        this.duck = duck;
    }

    @Override
    public void registerObserve(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        Iterator iterator = observers.iterator();
        while (iterator.hasNext()) {
            Observer observer = (Observer)iterator.next();
            observer.update(duck);
        }

    }
}
