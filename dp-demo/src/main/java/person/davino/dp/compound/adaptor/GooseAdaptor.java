package person.davino.dp.compound.adaptor;

import person.davino.dp.compound.Quackable;
import person.davino.dp.compound.geese.Goose;
import person.davino.dp.compound.observe.Observer;

public class GooseAdaptor implements Quackable{

    Goose goose;

    public GooseAdaptor(Goose goose) {
        this.goose = goose;
    }

    @Override
    public void quack() {
        goose.honk();
    }

    @Override
    public void registerObserve(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }
}
