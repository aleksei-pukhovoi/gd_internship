package oopDesignPatterns.behavioral.observer.subscriber;

import oopDesignPatterns.behavioral.observer.context.PostEvent;

public interface Subscriber {
    void update(PostEvent event);
}
