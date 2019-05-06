package observerpattern;

public class ForeCastConditions implements Subscriber, Display {

    private float temperature;
    private float humidity;
    private float pressure;
    private WeatherData weatherData;

    public ForeCastConditions(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerSubscriber(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        String format = String.format("Changes in temperature %f, pressure %f and humidity %f", temperature, pressure, humidity);
        System.out.println(format);
    }
}
