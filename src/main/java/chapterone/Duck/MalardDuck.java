package chapterone.Duck;

import chapterone.FlyBehaviour.FlyWithWings;
import chapterone.QuackBehaviour.Quack;

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
