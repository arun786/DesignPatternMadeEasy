package Singleton;

import java.io.*;

public class LazySingleton implements Serializable {
    private LazySingleton() {
    }

    private static LazySingleton instance;

    //Lazy and double locking
    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    public Object readResolve() {
        return instance;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        LazySingleton instance = LazySingleton.getInstance();
        String nameOfFile = "abc.txt";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nameOfFile))) {
            out.writeObject(instance);
        }

        LazySingleton lazySingleton;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nameOfFile))) {
            lazySingleton = (LazySingleton) in.readObject();
        }

        System.out.println(instance == lazySingleton);
    }
}
