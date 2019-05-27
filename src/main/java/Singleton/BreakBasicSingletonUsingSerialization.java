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
     * The readResolve method is called when ObjectInputStream has read an object from the stream
     * and is preparing to return it to the caller. ObjectInputStream checks whether the class of
     * the object defines the readResolve method. If the method is defined, the readResolve method
     * is called to allow the object in the stream to designate the object to be returned.
     * The object returned should be of a type that is compatible with all uses. If it is not compatible,
     * a ClassCastException will be thrown when the type mismatch is discovered.
     * @return - In order to make the singleton a singleton, we need to return the instance for the below method
     */
    protected Object readResolve() {
        return instance;
    }
}
