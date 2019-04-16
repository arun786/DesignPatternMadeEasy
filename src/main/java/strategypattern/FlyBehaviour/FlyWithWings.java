package strategypattern.FlyBehaviour;

public class FlyWithWings implements FlyBehaviour {
    private String name;

    public FlyWithWings(String name) {
        this.name = name;
    }

    public void fly() {
        System.out.println(name + " is flying");
    }
}
