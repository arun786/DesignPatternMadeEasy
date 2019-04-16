package chapterone.QuackBehaviour;

public class Quack implements QuackBehaviour {
    private String name;

    public Quack(String name) {
        this.name = name;
    }

    public void quack() {
        System.out.println(name + " quacks");
    }
}
