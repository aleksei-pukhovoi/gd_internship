package oopDesignPatterns.creational.abstractFactory.product;

public class RoundedTriangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Draw rounded triangle");
    }
}
