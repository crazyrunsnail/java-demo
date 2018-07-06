package person.davino.dp.compound.ducks;

import person.davino.dp.compound.Quackable;
import person.davino.dp.compound.observe.Observable;
import person.davino.dp.compound.observe.Observer;

public class DuckCall implements Quackable{

    Observable observable;

    public DuckCall( ) {
        this.observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("Kwak");
        notifyObservers();
    }

    @Override
    public void registerObserve(Observer observer) {
        observable.registerObserve(observer);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }
}
