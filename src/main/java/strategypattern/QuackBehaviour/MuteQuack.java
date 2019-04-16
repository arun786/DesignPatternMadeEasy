package strategypattern.QuackBehaviour;

public class MuteQuack implements QuackBehaviour {

    private String name;

    public MuteQuack(String name) {
        this.name = name;
    }

    public void quack() {
        System.out.println(name + " is a mute duck");
    }
}
