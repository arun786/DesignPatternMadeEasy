package strategypattern.QuackBehaviour;

public class Squeak implements QuackBehaviour {

    private String name;

    public Squeak(String name) {
        this.name = name;
    }

    public void quack() {
        System.out.println(name + "squeaks");
    }
}
