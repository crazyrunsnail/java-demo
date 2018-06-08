package person.davino.dp.decorator.component;

public class HouseBlend extends Beverage{

    public HouseBlend() {
        description = "Hose Blend Coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}
