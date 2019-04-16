package strategypattern;

import strategypattern.Duck.Duck;
import strategypattern.Duck.MalardDuck;
import strategypattern.Duck.RedHeadDuck;
import strategypattern.Duck.RubberDuck;
import strategypattern.FlyBehaviour.FlyNoWay;

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
