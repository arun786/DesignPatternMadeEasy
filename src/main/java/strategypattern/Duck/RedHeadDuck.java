package strategypattern.Duck;

import strategypattern.FlyBehaviour.FlyWithWings;
import strategypattern.QuackBehaviour.Squeak;

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
