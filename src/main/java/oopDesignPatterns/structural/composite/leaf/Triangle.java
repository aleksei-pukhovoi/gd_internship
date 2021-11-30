package oopDesignPatterns.structural.composite.leaf;

import oopDesignPatterns.structural.composite.component.Shape;

public class Triangle implements Shape {
    @Override
    public void draw(String fillColor) {
        System.out.println("Draw triangle with " + fillColor + " color");
    }
}
