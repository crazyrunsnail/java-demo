package person.davino.dp.compound.factory;

import person.davino.dp.compound.Quackable;
import person.davino.dp.compound.ducks.DuckCall;
import person.davino.dp.compound.ducks.MallarDuck;
import person.davino.dp.compound.ducks.RedHeadDuck;
import person.davino.dp.compound.ducks.RubberDuck;

public class DuckFactory extends AbstractFactory{
    @Override
    public Quackable createMallarDuck() {
        return new MallarDuck();
    }

    @Override
    public Quackable createRedHeadDuck() {
        return new RedHeadDuck();
    }

    @Override
    public Quackable createDuckCall() {
        return new DuckCall();
    }

    @Override
    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}
