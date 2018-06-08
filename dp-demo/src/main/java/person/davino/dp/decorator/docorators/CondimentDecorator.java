package person.davino.dp.decorator.docorators;

import person.davino.dp.decorator.component.Beverage;

public abstract class CondimentDecorator extends Beverage{
    private Beverage beverage;

    public CondimentDecorator(String description, Beverage beverage) {

        this.beverage = beverage;
    }

    protected Beverage getBeverage() {
        return this.beverage;
    }

    public abstract String getDescription();

}
