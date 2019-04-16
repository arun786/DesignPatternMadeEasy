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
