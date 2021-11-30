package oopDesignPatterns.structural.bridge.abstraction;

public class GreenColor implements Color{
    @Override
    public void applyColor() {
        System.out.println("green.");
    }
}
