package person.davino.dp.factory.v3;

import person.davino.dp.factory.Pizza;

/**
 * 这里使用一个抽象类, 将createPizza方法交给子类去实现
 * 这样可以不用管子类是用什么方法来实现的, 实现的是何种Pizza
 * 这也符合设计原则的: <em>对拓展开放, 对修改封闭</em>
 */
public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        return createPizza(type);
    }

    abstract Pizza createPizza(String type);
}
