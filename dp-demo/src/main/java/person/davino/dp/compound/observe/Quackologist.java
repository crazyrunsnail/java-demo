package person.davino.dp.compound.observe;

public class Quackologist implements Observer{
    @Override
    public void update(QuackObservable observable) {
        System.out.println("Quackologis:" + observable + " just quacked!");
    }
}
