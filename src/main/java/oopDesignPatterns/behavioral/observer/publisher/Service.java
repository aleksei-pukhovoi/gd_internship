package oopDesignPatterns.behavioral.observer.publisher;

import oopDesignPatterns.behavioral.observer.context.PostEvent;
import oopDesignPatterns.behavioral.observer.subscriber.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class Service {
    List<Subscriber> subscribers;

    public Service() {
        this.subscribers = new ArrayList<>();
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        if (subscriber != null) {
            subscribers.remove(subscriber);
        }
    }

    public void notify(PostEvent event) {
        subscribers.forEach(subscriber -> subscriber.update(event));
    }
}
