package oopDesignPatterns.behavioral.observer;

import oopDesignPatterns.behavioral.observer.publisher.BasePublisher;
import oopDesignPatterns.behavioral.observer.publisher.Publisher;
import oopDesignPatterns.behavioral.observer.publisher.Service;
import oopDesignPatterns.behavioral.observer.subscriber.BaseSubscriber;
import oopDesignPatterns.behavioral.observer.subscriber.Subscriber;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        Subscriber subscriber = new BaseSubscriber();
        Publisher publisher = new BasePublisher(service);
        service.addSubscriber(subscriber);
        publisher.postEvent("Aleksei", "observer implementation");
    }
}
