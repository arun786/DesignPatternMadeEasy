# Design Patterns 

## Strategy Pattern


### Principals

    1. Identify the aspects of your application that vary and seperate out what stays the same.
    2. Program to an interface and not to an implementation.
    3. favour composition over Inheritence.
    
    Strategy pattern defines a family of algorithm, encapsulates each one, and makes them interchangeable.
    
    
        
        public interface FlyBehaviour {
            void fly();
        }


        public class FlyNoWay implements FlyBehaviour {
        
            private String name;
        
            public FlyNoWay(String name) {
                this.name = name;
            }
        
            public void fly() {
                System.out.println(name + " cannot fly");
            }
        }
    
    
        public class FlyWithWings implements FlyBehaviour {
            private String name;
        
            public FlyWithWings(String name) {
                this.name = name;
            }
        
            public void fly() {
                System.out.println(name + " is flying");
            }
        }


        public interface QuackBehaviour {
            void quack();
        }

        
        public class MuteQuack implements QuackBehaviour {
        
            private String name;
        
            public MuteQuack(String name) {
                this.name = name;
            }
        
            public void quack() {
                System.out.println(name + " is a mute duck");
            }
        }

        
        public class Quack implements QuackBehaviour {
            private String name;
        
            public Quack(String name) {
                this.name = name;
            }
        
            public void quack() {
                System.out.println(name + " quacks");
            }
        }

        
        public class Squeak implements QuackBehaviour {
        
            private String name;
        
            public Squeak(String name) {
                this.name = name;
            }
        
            public void quack() {
                System.out.println(name + "squeaks");
            }
        }


        @Getter
        @Setter
        public abstract class Duck {
        
            protected FlyBehaviour flyBehaviour;
        
            protected QuackBehaviour quackBehaviour;
        
            public abstract void swim();
        
            public abstract void display();
        
            public void performFly() {
                flyBehaviour.fly();
            }
        
            public void performQuack() {
                quackBehaviour.quack();
            }
        }

        
        public class MalardDuck extends Duck {
        
            private String name;
        
            public MalardDuck(String name) {
                this.name = name;
                flyBehaviour = new FlyWithWings(name);
                quackBehaviour = new Quack(name);
        
            }
        
            public void swim() {
                System.out.println(name + " is Swimming");
            }
        
            public void display() {
                System.out.println(name + " looks beautiful");
            }
        
        }


        public class RedHeadDuck extends Duck {
            private String name;
        
            public RedHeadDuck(String name) {
                this.name = name;
                flyBehaviour = new FlyWithWings(name);
                quackBehaviour = new Squeak(name);
            }
        
            public void swim() {
                System.out.println(name + " is swimming");
        
            }
        
            public void display() {
                System.out.println(name + " is red in color");
            }
        }


        public class RubberDuck extends Duck {
            private String name;
        
            public RubberDuck(String name) {
                this.name = name;
                flyBehaviour = new FlyNoWay(name);
                quackBehaviour = new MuteQuack(name);
            }
        
            public void swim() {
                System.out.println(name + " cannot swim");
            }
        
            public void display() {
                System.out.println(name + " is rubber coated duck");
            }
        }



        public class AllDuckControlRoom {
            public static void main(String[] args) {
        
                String mallardDuck = "Mallard Duck";
        
                Duck duck = new MalardDuck(mallardDuck);
        
                duck.performFly();
                duck.performQuack();
                duck.swim();
                duck.display();
                duck.setFlyBehaviour(new FlyNoWay(mallardDuck));
                duck.performFly();
        
                String redHeadDuck = "Red Head Duck";
        
                duck = new RedHeadDuck(redHeadDuck);
                duck.performFly();
                duck.performQuack();
                duck.swim();
                duck.display();
        
                String rubberDuck = "RubberDuck";
        
                duck = new RubberDuck(rubberDuck);
                duck.performFly();
                duck.display();
                duck.performQuack();
                duck.swim();
            }
        }

# Polymorphism

## Method Overloading

### Best Practises of Method Overloading

####  Avoid using the same number of parameter and similar types for method overloading

    import java.util.ArrayList;
    import java.util.LinkedList;
    import java.util.List;
    
    public class MethodOverLoading {
    
        public static void main(String[] args) {
    
            ConfusionOverLoading confusionOverLoading = new ConfusionOverLoading();
            List<String> names = new ArrayList<>();
            List<String> boys = new LinkedList<>();
            confusionOverLoading.checkDuplicates(names);
            confusionOverLoading.checkDuplicates(boys);
    
            //for both the above call the method with parameter as List<String> is called
    
        }
    
    
    }
    
    class ConfusionOverLoading {
    
        /**
         * avoid using the same number of parameter and similar types for method overloading
         */
        public void checkDuplicates(List<String> names) {
            System.out.println("checkDuplicates with List as argument is called");
        }
    
        public void checkDuplicates(ArrayList<String> names) {
            System.out.println("checkDuplicates with ArrayList as argument is called");
        }
    
        public void checkDuplicates(LinkedList<String> names) {
            System.out.println("checkDuplicates with LinkedList as argument is called");
        }
    }
    

## Observer Pattern

    Defines one to many relationship between objects such that when there is a change in the state of one object,
    all its dependents are notified.
    
    basically publisher - subscriber relationship
    
    
        public interface Publisher{
        
            void registerSubscriber(Subscriber subscriber);
        
            void notifySubscriber();
        }
        
        
        interface Subscriber {
        
            void update(float temperature, float humidity, float pressure);
        }
        
        interface Display {
        
            void display();
        }


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


        
        public class App {
        
            public static void main(String[] args) {
                WeatherData weatherData = new WeatherData();
                new CurrentConditions(weatherData);
                new ForeCastConditions(weatherData);
                weatherData.setMeasurement(10, 12, 14);
            }
        }
