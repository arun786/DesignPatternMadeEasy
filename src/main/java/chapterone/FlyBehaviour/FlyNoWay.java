package chapterone.FlyBehaviour;

public class FlyNoWay implements FlyBehaviour {

    private String name;

    public FlyNoWay(String name) {
        this.name = name;
    }

    public void fly() {
        System.out.println(name + " cannot fly");
    }
}
