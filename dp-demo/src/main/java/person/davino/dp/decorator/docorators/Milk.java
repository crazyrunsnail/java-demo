package person.davino.dp.decorator.docorators;

import person.davino.dp.decorator.component.Beverage;

public class Milk extends CondimentDecorator{
    public Milk(String description, Beverage beverage) {
        super("Milk", beverage);
    }

    @Override
    public String getDescription() {
        return getBeverage().getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        return 10D + getBeverage().cost(); // 关键点, 可以调用父类的方法, 可以加上前置和后置方法
    }
}
