package oopDesignPatterns.behavioral.strategy.strategy;

public class BusStrategy implements PathStrategy {
    @Override
    public void createPath() {
        System.out.println("Looking for best busses");
    }
}
