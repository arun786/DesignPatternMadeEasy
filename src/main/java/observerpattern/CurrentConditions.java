package observerpattern;

public class CurrentConditions implements Subscriber, Display {

    private float temperature;
    private float humidity;
    private WeatherData weatherData;

    public CurrentConditions(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerSubscriber(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        String format = String.format("Changes in temperature %f and humidity %f", temperature, humidity);
        System.out.println(format);
    }
}
