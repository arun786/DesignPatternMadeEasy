package observerpattern;

public class App {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        new CurrentConditions(weatherData);
        new ForeCastConditions(weatherData);
        weatherData.setMeasurement(10, 12, 14);
        weatherData.setMeasurement(15, 12, 14);
    }
}
