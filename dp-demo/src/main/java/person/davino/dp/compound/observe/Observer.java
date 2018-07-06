package person.davino.dp.compound.observe;

import person.davino.dp.compound.Quackable;

public interface Observer {
    void update(QuackObservable observable);
}
