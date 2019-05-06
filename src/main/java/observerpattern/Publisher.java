package observerpattern;

public interface Publisher {

    void registerSubscriber(Subscriber subscriber);

    void notifySubscriber();
}


interface Subscriber {

    void update(float temperature, float humidity, float pressure);
}

interface Display {

    void display();
}
