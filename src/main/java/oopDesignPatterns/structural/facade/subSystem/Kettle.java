package oopDesignPatterns.structural.facade.subSystem;

public class Kettle implements Smart {
    @Override
    public void on() {
        System.out.println("Kettle is on. Working...");
    }
}
