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