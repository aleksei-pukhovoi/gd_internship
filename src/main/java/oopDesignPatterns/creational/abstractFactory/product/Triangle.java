package oopDesignPatterns.creational.abstractFactory.product;

public class Triangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Draw triangle");
    }
}
