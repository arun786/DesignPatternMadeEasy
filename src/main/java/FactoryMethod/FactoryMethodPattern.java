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