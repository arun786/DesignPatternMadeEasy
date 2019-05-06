package observerpattern;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Publisher {

    private float temp;
    private float humidity;
    private float pressure;

    private List<Subscriber> subscribers;

    public WeatherData() {
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void registerSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void notifySubscriber() {
        subscribers.forEach(subscriber -> subscriber.update(temp, humidity, pressure));
    }

    private void measurementsChanged() {
        notifySubscriber();
    }

    public void setMeasurement(float temp, float humidity, float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;

        measurementsChanged();
    }
}
