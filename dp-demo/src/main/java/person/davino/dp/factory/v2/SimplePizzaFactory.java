package person.davino.dp.factory.v2;

import person.davino.dp.factory.CheesePizza;
import person.davino.dp.factory.GreekPizza;
import person.davino.dp.factory.PepperoniPizza;
import person.davino.dp.factory.Pizza;

public class SimplePizzaFactory {
    public Pizza create(String type) {
        Pizza pizza = null;
        switch (type) {
            case "cheese":
                pizza =  new CheesePizza();
                break;
            case "geek":
                pizza = new GreekPizza();
                break;
            case "pepperoni":
                pizza =  new PepperoniPizza();
                break;
        }
        return pizza;
    }
}
