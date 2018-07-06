package person.davino.dp.compound.ducks;

import person.davino.dp.compound.Quackable;
import person.davino.dp.compound.observe.Observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Flock implements Quackable{
    List<Quackable> quackers = new ArrayList();

    public void add(Quackable quackable) {
        quackers.add(quackable);
    }

    @Override
    public void quack() {
        Iterator<Quackable> iterator = quackers.iterator();
        while (iterator.hasNext()) {
            iterator.next().quack();
        }
    }

    @Override
    public void registerObserve(Observer observer) {
        for (Quackable quackable: quackers) {
            quackable.registerObserve(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (Quackable quackable: quackers) {
            quackable.notifyObservers();
        }
    }
}
