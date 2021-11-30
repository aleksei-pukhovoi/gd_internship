package oopDesignPatterns.creational.abstractFactory.product;

public class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Draw rectangle");
    }
}
