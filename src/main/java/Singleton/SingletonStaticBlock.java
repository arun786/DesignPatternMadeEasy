package Singleton;

import java.io.*;

public class SingletonStaticBlock implements Serializable {

    private SingletonStaticBlock() throws IOException {
        System.out.println("Initializing singleton class");
        File.createTempFile("abc.", ".");
    }

    private static SingletonStaticBlock instance;

    static {
        try {
            instance = new SingletonStaticBlock();
        } catch (IOException e) {
            System.err.println("Error in initializing Sigleton class");
        }
    }

    public static SingletonStaticBlock getInstance() {
        return instance;
    }

    public Object readResolve() {
        return instance;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String nameOfFile = "abc.txt";
        SingletonStaticBlock instance = SingletonStaticBlock.getInstance();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nameOfFile))) {
            out.writeObject(instance);
        }
        SingletonStaticBlock singletonStaticBlock = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nameOfFile))) {
            singletonStaticBlock = (SingletonStaticBlock) in.readObject();
        }

        System.out.println(instance == singletonStaticBlock);
    }
}
