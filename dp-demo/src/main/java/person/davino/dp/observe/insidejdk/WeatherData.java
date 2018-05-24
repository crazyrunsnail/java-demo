package person.davino.dp.observe.insidejdk;

import person.davino.dp.observe.improve.Observer;
import person.davino.dp.observe.improve.Subject;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class WeatherData extends Observable{

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




}
