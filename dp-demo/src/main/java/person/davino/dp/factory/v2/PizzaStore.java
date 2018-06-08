package person.davino.dp.factory.v2;

import person.davino.dp.factory.Pizza;

/**
 * 这里有一些改良: factory可以动态改变, 而不用修改 {@link #orderPizza(String)} 的方法
 */
public class PizzaStore {

    private final SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza = this.factory.create(type);
        return pizza;
    }
}
