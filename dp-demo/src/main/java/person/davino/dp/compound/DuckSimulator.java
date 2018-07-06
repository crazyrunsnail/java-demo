package person.davino.dp.compound;

import person.davino.dp.compound.adaptor.GooseAdaptor;
import person.davino.dp.compound.decorator.QuackCounter;
import person.davino.dp.compound.ducks.*;
import person.davino.dp.compound.geese.Goose;
import person.davino.dp.compound.observe.Quackologist;

public class DuckSimulator {

    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        simulator.simulate();
    }

    void simulate() {
        Quackable mallarDuck = new QuackCounter(new MallarDuck());
        Quackable redHeadDuck = new QuackCounter(new RedHeadDuck());
        Quackable duckCall = new QuackCounter(new DuckCall());
        Quackable rubberDuck = new QuackCounter(new RubberDuck());

        // Adaptor 模式, 转化鸭和鹅的关系
        Quackable gooseAdaptor = new GooseAdaptor(new Goose());

        System.out.println("\tDuck Simulator");

        simulate(mallarDuck);
        simulate(redHeadDuck);
        simulate(duckCall);
        simulate(rubberDuck);
        simulate(gooseAdaptor);

        System.out.printf("The ducks quackd %d times \n", QuackCounter.getQuacks());


        System.out.println("Simulate with flock and observe!");
        Flock flock = new Flock();
        flock.add(mallarDuck);
        flock.add(redHeadDuck);
        flock.add(duckCall);
        flock.add(rubberDuck);

        Quackologist quackologist = new Quackologist();
        flock.registerObserve(quackologist);

        simulate(flock);
    }

    // 用到了策略模式 stratege
    void simulate(Quackable quackable) {
        quackable.quack();
    }
}
