package person.davino.dp.factory.v1;

import person.davino.dp.factory.CheesePizza;
import person.davino.dp.factory.GreekPizza;
import person.davino.dp.factory.PepperoniPizza;
import person.davino.dp.factory.Pizza;

/**
 * 直接在过程里声明Pizza
 * 如果需要改的话, 需要在 case 里直接改, 这里变化的代码, 可以抽离出来
 */
public class PizzaStore {

    public Pizza orderPizza(String type) {
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

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
 }
