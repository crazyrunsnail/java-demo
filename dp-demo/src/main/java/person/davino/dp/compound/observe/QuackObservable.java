package person.davino.dp.compound.observe;

public interface QuackObservable {
    public void registerObserve(Observer observer);
    public void notifyObservers();
}
