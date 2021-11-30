package oopDesignPatterns.structural.facade.subSystem;

public class Light implements Smart {
    @Override
    public void on() {
        System.out.println("Light is on. Blue color");
    }
}
