package oopDesignPatterns.behavioral.strategy.strategy;

public class CarStrategy implements PathStrategy {
    @Override
    public void createPath() {
        System.out.println("Creating path for a car");
    }
}
