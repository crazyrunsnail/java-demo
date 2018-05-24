package person.davino.dp.observe.improve;

import person.davino.dp.observe.wrongexample.Display;
import person.davino.dp.observe.wrongexample.OneDisplay;
import person.davino.dp.observe.wrongexample.TwoDisplay;

import java.util.Arrays;
import java.util.List;

public class WeatherData  implements Subject{

    List<Observer> observers = Arrays.asList();


    public void measurementsChanged() {
        notifyObservers();
    }


    private float getTempurature() {
        return 1.0F;
    }

    private float getHumidity() {
        return 2.0F;
    }

    private float getPressure() {
        return 3.0F;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i >= 0 )
            observers.remove(i);
    }

    @Override
    public void notifyObservers() {
        for (Observer o: observers) {
            o.update(getTempurature(), getHumidity(), getPressure());
        }
    }



}
