package person.davino.dp.compound;

import person.davino.dp.compound.observe.QuackObservable;

public interface Quackable extends QuackObservable{
    void quack();
}
