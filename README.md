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


# Simple Factory Pattern


    package simplefactory;
    
    public class SimpleFactory {
    
        public static void main(String[] args) {
    
            PizzaFactory pizzaFactory = new PizzaFactory();
    
            PizzaStore pizzaStore = new PizzaStore(pizzaFactory);
            pizzaStore.orderPizza("Cheese");
    
        }
    }
    
    abstract class Pizza {
        abstract void prepare();
    
        abstract void bake();
    
        abstract void cut();
    
        abstract void box();
    }
    
    
    class CheesePizza extends Pizza {
    
        @Override
        void prepare() {
            System.out.println("Preparing Cheese Pizza..");
        }
    
        @Override
        void bake() {
            System.out.println("Baking Cheese Pizza..");
        }
    
        @Override
        void cut() {
            System.out.println("Cutting Cheese Pizza..");
        }
    
        @Override
        void box() {
            System.out.println("Boxing Cheese Pizza..");
        }
    }
    
    
    class VeggiePizza extends Pizza {
    
        @Override
        void prepare() {
            System.out.println("Preparing Veggie Pizza..");
        }
    
        @Override
        void bake() {
            System.out.println("Baking Veggie Pizza..");
        }
    
        @Override
        void cut() {
            System.out.println("Cutting Veggie Pizza..");
        }
    
        @Override
        void box() {
            System.out.println("Boxing Veggie Pizza..");
        }
    }
    
    class PizzaFactory {
    
        Pizza createPizza(String typeOfPizza) {
    
            if (typeOfPizza.equalsIgnoreCase("Cheese")) {
                return new CheesePizza();
            } else if (typeOfPizza.equalsIgnoreCase("Veggie")) {
                return new VeggiePizza();
            }
    
            return null;
        }
    }
    
    class PizzaStore {
    
        private PizzaFactory pizzaFactory;
    
        public PizzaStore(PizzaFactory pizzaFactory) {
            this.pizzaFactory = pizzaFactory;
        }
    
        public Pizza orderPizza(String typeOfPizza) {
    
            Pizza pizza = pizzaFactory.createPizza(typeOfPizza);
    
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
    
            return pizza;
        }
    }
    
    
# Factory Method Pattern

## Defines an interface for creating an object but lets subclasses decide which class to instantiate.


    package FactoryMethod;
    
    public class FactoryMethodPattern {
        public static void main(String[] args) {
    
            PizzaStore pizzaStore = new NYPizzaStore();
            pizzaStore.orderPizza("Cheese");
    
        }
    }
    
    abstract class Pizza {
        abstract void prepare();
    
        abstract void bake();
    
        abstract void cut();
    
        abstract void box();
    }
    
    class NYStyleCheesePizza extends Pizza {
    
        @Override
        void prepare() {
            System.out.println("Preparing NYStyleCheesePizza");
        }
    
        @Override
        void bake() {
            System.out.println("Baking NYStyleCheesePizza");
        }
    
        @Override
        void cut() {
            System.out.println("Cutting NYStyleCheesePizza");
        }
    
        @Override
        void box() {
            System.out.println("Boxing NYStyleCheesePizza");
        }
    }
    
    class NYStyleVeggiePizza extends Pizza {
    
        @Override
        void prepare() {
            System.out.println("Preparing NYStyleVeggiePizza");
        }
    
        @Override
        void bake() {
            System.out.println("Baking NYStyleVeggiePizza");
        }
    
        @Override
        void cut() {
            System.out.println("Cutting NYStyleVeggiePizza");
        }
    
        @Override
        void box() {
            System.out.println("Boxing NYStyleVeggiePizza");
        }
    }
    
    class AZStyleVeggiePizza extends Pizza {
    
        @Override
        void prepare() {
            System.out.println("Preparing AZStyleVeggiePizza");
        }
    
        @Override
        void bake() {
            System.out.println("Baking AZStyleVeggiePizza");
        }
    
        @Override
        void cut() {
            System.out.println("Cutting AZStyleVeggiePizza");
        }
    
        @Override
        void box() {
            System.out.println("Boxing AZStyleVeggiePizza");
        }
    }
    
    class AZStyleCheesePizza extends Pizza {
    
        @Override
        void prepare() {
            System.out.println("Preparing AZStyleCheesePizza");
        }
    
        @Override
        void bake() {
            System.out.println("Baking AZStyleCheesePizza");
        }
    
        @Override
        void cut() {
            System.out.println("Cutting AZStyleCheesePizza");
        }
    
        @Override
        void box() {
            System.out.println("Boxing AZStyleCheesePizza");
        }
    }
    
    
    abstract class PizzaStore {
    
        public Pizza orderPizza(String typeOfPizza) {
            Pizza pizza = createPizza(typeOfPizza);
            pizza.prepare();
            pizza.cut();
            pizza.bake();
            pizza.box();
            return pizza;
        }
    
        /**
         * Factory Method Pattern
         */
        protected abstract Pizza createPizza(String typeOfPizza);
    
    }
    
    class NYPizzaStore extends PizzaStore {
    
        @Override
        protected Pizza createPizza(String typeOfPizza) {
    
            if (typeOfPizza.equalsIgnoreCase("Cheese")) {
                return new NYStyleCheesePizza();
            }
            return new NYStyleVeggiePizza();
        }
    }
    
    class AZPizzaStore extends PizzaStore {
    
        @Override
        protected Pizza createPizza(String typeOfPizza) {
            if (typeOfPizza.equalsIgnoreCase("Cheese")) {
                return new AZStyleCheesePizza();
            }
    
            return new AZStyleVeggiePizza();
        }
    }
    
    
## OCP

    package SOLID;
    
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.ToString;
    
    import java.util.Arrays;
    import java.util.List;
    import java.util.stream.Stream;
    
    public class OCP {
    
        public static void main(String[] args) {
            Product bike = new Product("bike", Color.BLUE, Size.HUGE);
            Product car = new Product("car", Color.GREEN, Size.SMALL);
            Product train = new Product("train", Color.RED, Size.MEDIUM);
            Product truck = new Product("truck", Color.RED, Size.LARGE);
    
            List<Product> products = Arrays.asList(bike, car, train, truck);
            ProductFilter productFilter = new ProductFilter();
            productFilter.filterProductByColor(products, Color.BLUE).forEach(System.out::println);
            productFilter.filterProductBySize(products, Size.SMALL).forEach(System.out::println);
            productFilter.filterProductByNameAndSize(products, Size.MEDIUM, Color.RED).forEach(System.out::println);
    
    
            System.out.println("...Better OCP...");
            BetterFilter betterFilter = new BetterFilter();
            betterFilter.filter(products, new ColorSpecification(Color.RED)).forEach(System.out::println);
            betterFilter.filter(products, new SizeSpecification(Size.SMALL)).forEach(System.out::println);
    
    
            betterFilter.filter(products, new AndSpecification<>(new ColorSpecification(Color.RED), new SizeSpecification(Size.MEDIUM))).forEach(System.out::println);
        }
    }
    
    enum Color {
        RED, BLUE, GREEN
    }
    
    enum Size {
        SMALL, MEDIUM, LARGE, HUGE
    }
    
    
    @AllArgsConstructor
    @Getter
    @ToString
    class Product {
        private String name;
        private Color color;
        private Size size;
    }
    
    /**
     * Not following Open closed Principle
     */
    class ProductFilter {
    
        Stream<Product> filterProductByColor(List<Product> products, Color color) {
            return products.stream().filter(product -> product.getColor() == color);
        }
    
        Stream<Product> filterProductBySize(List<Product> products, Size size) {
            return products.stream().filter(product -> product.getSize() == size);
        }
    
        Stream<Product> filterProductByNameAndSize(List<Product> products, Size size, Color color) {
            return products.stream().filter(product -> product.getSize() == size && product.getColor() == color);
        }
    }
    
    /**
     * Following open closed principle
     */
    
    
    interface Specification<T> {
        boolean isSatisfied(T item);
    }
    
    interface Filter<T> {
        Stream<T> filter(List<T> items, Specification<T> specification);
    }
    
    
    @AllArgsConstructor
    class ColorSpecification implements Specification<Product> {
    
        private Color color;
    
        @Override
        public boolean isSatisfied(Product item) {
            return item.getColor() == color;
        }
    }
    
    @AllArgsConstructor
    class SizeSpecification implements Specification<Product> {
    
        private Size size;
    
        @Override
        public boolean isSatisfied(Product item) {
            return item.getSize() == size;
        }
    }
    
    @AllArgsConstructor
    class AndSpecification<T> implements Specification<T> {
    
        private Specification<T> first, second;
    
        @Override
        public boolean isSatisfied(T item) {
            return first.isSatisfied(item) && second.isSatisfied(item);
        }
    }
    
    class BetterFilter implements Filter<Product> {
    
        @Override
        public Stream<Product> filter(List<Product> items, Specification<Product> specification) {
            return items.stream().filter(item -> specification.isSatisfied(item));
        }
    }
    
## Liskov Substitution principle

    package SOLID;
    
    import lombok.*;
    
    /**
     * Object of Parent class can be replaced with object of child class without any negative side effects
     */
    public class LiskhovSubstitutionPrinciple {
    
        public static void main(String[] args) {
            Rectangle rectangle = new Rectangle(2, 3);
            Demo.useIt(rectangle);
    
            Rectangle square = new Square(2);
            Demo.useIt(square);
        }
    
    }
    
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    class Rectangle {
        protected int width;
        protected int height;
    
        public int getArea() {
            return this.width * this.height;
        }
    }
    
    
    @NoArgsConstructor
    class Square extends Rectangle {
    
        public Square(int size) {
            this.height = this.width = size;
        }
    
        @Override
        public void setWidth(int width) {
            super.setWidth(width);
            super.setHeight(width);
        }
    
        @Override
        public void setHeight(int height) {
            super.setHeight(height);
            super.setWidth(height);
        }
    }
    
    class Demo {
        static void useIt(Rectangle rectangle) {
    
            int width = rectangle.getWidth();
            rectangle.setHeight(10);
    
            System.out.println("Expected area = " + width * 10 + " actual output will be " + rectangle.getArea());
        }
    }
    
 
 ## Method Chaining
 
    package Builder;
    
    import lombok.Getter;
    import lombok.ToString;
    
    public class MethodChaining {
    
        public static void main(String[] args) {
    
            Student student1 = new Student();
            student1.setAddress("Scottsdale").setId("1").setName("Arun");
            Student student = new Student();
            student.setName("Pushpa").setId("2").setAddress("Scottsdale");
            System.out.println(student1);
            System.out.println(student);
    
        }
    }
    
    @Getter
    @ToString
    class Student {
        private String id;
        private String name;
        private String address;
    
        public Student setId(String id) {
            this.id = id;
            return this;
    
        }
    
        public Student setName(String name) {
            this.name = name;
            return this;
        }
    
        public Student setAddress(String address) {
            this.address = address;
            return this;
        }
    }
    

## Cons of Method Chaining - It may be provide inconsistent result if executed in a multithreaded environment

    @Getter
    class StudentReceiver {
        private final Student student = new Student();
    
        public StudentReceiver() {
    
            Thread t1 = new Thread(() -> student.setId("1").setAddress("Scottsdale").setName("Arun"));
            Thread t2 = new Thread(() -> student.setId("1").setAddress("Scottsdale").setName("Pushpa"));
    
            t1.start();
            t2.start();
        }
    }
    
    when we call it from the main method, it may give inconsistent result.
    
    
## Builder Pattern - this comes to the rescue

    package Builder;
    
    import lombok.Getter;
    import lombok.Setter;
    import lombok.ToString;
    
    public class BuilderPatternDefined {
    
        public static void main(String[] args) {
            Player player = PlayerBuilder.newInstance().setId("1").setName("Arun").build();
            System.out.println(player);
        }
    }
    
    @ToString
    class Player {
        private String id;
        private String name;
    
        public Player(PlayerBuilder playerBuilder) {
            this.id = playerBuilder.getId();
            this.name = playerBuilder.getName();
        }
    }
    
    @Getter
    @Setter
    class PlayerBuilder {
        private String id;
        private String name;
    
        private PlayerBuilder() {
        }
    
        public static PlayerBuilder newInstance() {
            return new PlayerBuilder();
        }
    
        public PlayerBuilder setId(String id) {
            this.id = id;
            return this;
        }
    
        public PlayerBuilder setName(String name) {
            this.name = name;
            return this;
        }
    
        public Player build() {
            return new Player(this);
        }
    }
  
    
