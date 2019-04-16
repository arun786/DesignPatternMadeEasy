package chapterone.Duck;

import chapterone.FlyBehaviour.FlyBehaviour;
import chapterone.QuackBehaviour.QuackBehaviour;
import lombok.Getter;
import lombok.Setter;

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
