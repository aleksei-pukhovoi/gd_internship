package oopDesignPatterns.behavioral.strategy.strategy;

public class WalkStrategy implements PathStrategy {
    @Override
    public void createPath() {
        System.out.println("Looking for the best places");
    }
}
