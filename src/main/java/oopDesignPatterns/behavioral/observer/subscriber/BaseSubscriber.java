package oopDesignPatterns.behavioral.observer.subscriber;

import oopDesignPatterns.behavioral.observer.context.PostEvent;

public class BaseSubscriber implements Subscriber {
    @Override
    public void update(PostEvent event) {
        System.out.println("Notifying...");
        System.out.println(event.getContent());
    }
}
