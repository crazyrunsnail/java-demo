package person.davino.dp.compound.geese;

import person.davino.dp.compound.Quackable;
import person.davino.dp.compound.adaptor.GooseAdaptor;
import person.davino.dp.compound.decorator.QuackCounter;
import person.davino.dp.compound.ducks.DuckCall;
import person.davino.dp.compound.ducks.MallarDuck;
import person.davino.dp.compound.ducks.RedHeadDuck;
import person.davino.dp.compound.ducks.RubberDuck;
import person.davino.dp.compound.factory.AbstractFactory;
import person.davino.dp.compound.factory.DuckCounterFactory;

public class FactoryDuckSimulator {

    public static void main(String[] args) {
        FactoryDuckSimulator simulator = new FactoryDuckSimulator();
        AbstractFactory abstractFactory = new DuckCounterFactory();
        simulator.simulate(abstractFactory);
    }

    void simulate(AbstractFactory factory) {
        Quackable mallarDuck = factory.createMallarDuck();
        Quackable redHeadDuck = factory.createRedHeadDuck();
        Quackable duckCall = factory.createDuckCall();
        Quackable rubberDuck = factory.createRubberDuck();

        // Adaptor 模式, 转化鸭和鹅的关系
        Quackable gooseAdaptor = new GooseAdaptor(new Goose());

        System.out.println("\tDuck Simulator");

        simulate(mallarDuck);
        simulate(redHeadDuck);
        simulate(duckCall);
        simulate(rubberDuck);
        simulate(gooseAdaptor);

        System.out.printf("The ducks quackd %d times \n", QuackCounter.getQuacks());
    }

    // 用到了策略模式 stratege
    void simulate(Quackable quackable) {
        quackable.quack();
    }
}
