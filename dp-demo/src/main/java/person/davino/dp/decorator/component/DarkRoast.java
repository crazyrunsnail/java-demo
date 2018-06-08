package person.davino.dp.decorator.component;

public class DarkRoast extends Beverage{
    public DarkRoast(String description) {
        description = "Dark Roast Coffee";
    }

    @Override
    public double cost() {
        return .56;
    }
}
