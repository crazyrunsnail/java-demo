package person.davino.dp.compound.factory;

import person.davino.dp.compound.Quackable;

public abstract class AbstractFactory {
    abstract public Quackable createMallarDuck();
    public abstract Quackable createRedHeadDuck();
    public abstract Quackable createDuckCall();
    public abstract Quackable createRubberDuck();
}
