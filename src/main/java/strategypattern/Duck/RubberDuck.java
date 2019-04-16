package strategypattern.Duck;

import strategypattern.FlyBehaviour.FlyNoWay;
import strategypattern.QuackBehaviour.MuteQuack;

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
