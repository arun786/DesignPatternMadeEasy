package Singleton;

import java.io.*;

public class InnerStaticSingleton implements Serializable {

    private InnerStaticSingleton() {
    }

    private static class Impl {
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    public static InnerStaticSingleton getInstance() {
        return Impl.INSTANCE;
    }

    private Object readResolve() {
        return Impl.INSTANCE;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        InnerStaticSingleton instance = InnerStaticSingleton.getInstance();

        String nameOfFile = "abc.txt";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nameOfFile))) {
            out.writeObject(instance);
        }

        InnerStaticSingleton innerStaticSingleton;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nameOfFile))) {
            innerStaticSingleton = (InnerStaticSingleton) in.readObject();
        }

        System.out.println(instance == innerStaticSingleton);
    }
}
