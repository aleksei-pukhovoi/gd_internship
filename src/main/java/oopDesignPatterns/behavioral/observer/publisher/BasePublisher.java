package oopDesignPatterns.behavioral.observer.publisher;

import oopDesignPatterns.behavioral.observer.context.PostEvent;

public class BasePublisher implements Publisher {

    private Service service;

    public BasePublisher(Service service) {
        this.service = service;
    }

    @Override
    public void postEvent(String name, String content) {
        System.out.println("Publishing...");
        System.out.println(content);
        service.notify(new PostEvent(name, content));
    }
}
