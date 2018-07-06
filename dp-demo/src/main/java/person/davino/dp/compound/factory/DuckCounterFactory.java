package person.davino.dp.compound.factory;

import person.davino.dp.compound.Quackable;
import person.davino.dp.compound.decorator.QuackCounter;
import person.davino.dp.compound.ducks.DuckCall;
import person.davino.dp.compound.ducks.MallarDuck;
import person.davino.dp.compound.ducks.RedHeadDuck;
import person.davino.dp.compound.ducks.RubberDuck;

public class DuckCounterFactory extends AbstractFactory{
    @Override
    public Quackable createMallarDuck() {
        return new QuackCounter(new MallarDuck());
    }

    @Override
    public Quackable createRedHeadDuck() {
        return new QuackCounter(new RedHeadDuck());
    }

    @Override
    public Quackable createDuckCall() {
        return new QuackCounter(new DuckCall());
    }

    @Override
    public Quackable createRubberDuck() {
        return new QuackCounter(new RubberDuck());
    }
}
