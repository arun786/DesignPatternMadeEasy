package Singleton;

import lombok.Getter;
import lombok.Setter;

import java.io.*;

public class BreakBasicSingletonUsingSerialization {

    private static void saveFile(Singlet singleton, String nameOfFile) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream(nameOfFile);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(singleton);
        }
    }

    private static Singlet readFile(String nameOfFile) throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(nameOfFile);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (Singlet) objectInputStream.readObject();
        }
    }


    public static void main(String[] args) throws Exception {
        Singlet instance = Singlet.getInstance();
        String nameOfFile = "abc.txt";
        saveFile(instance, nameOfFile);

        Singlet singlet = readFile(nameOfFile);

        System.out.println(instance == singlet); //This will return false, which means 2 instances of Siglet is created

    }

}


@Getter
@Setter
class Singlet implements Serializable {
    private String name;

    private Singlet() {
    }

    private static final Singlet instance = new Singlet();

    public static Singlet getInstance() {
        return instance;
    }

    /**
     * @return - In order to make the singleton a singleton, we need to return the instance for the below method
     */
    protected Object readResolve() {
        return instance;
    }
}
