package Singleton;

import lombok.Getter;
import lombok.Setter;

public class BasicSingleton {

    public static void main(String[] args) {
        Single instance = Single.getInstance();
        instance.setName("Arun");
        String name = instance.getName();
        System.out.println(name);
    }

}

@Getter
@Setter
class Single {
    private String name;
    /**
     * Make a constructor private and create an instance inside the class and
     * define a public method to get the instance
     */
    private Single() {
    }

    private static final Single instance = new Single();

    public static Single getInstance() {
        return instance;
    }
}
