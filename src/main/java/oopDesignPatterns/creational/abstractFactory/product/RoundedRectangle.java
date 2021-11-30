package oopDesignPatterns.creational.abstractFactory.product;

public class RoundedRectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Draw rounded rectangle");
    }
}
