package oopDesignPatterns.structural.bridge.abstraction;

public class RedColor implements Color{
    @Override
    public void applyColor() {
        System.out.println("red.");
    }
}
