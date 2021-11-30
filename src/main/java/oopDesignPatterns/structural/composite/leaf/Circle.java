package oopDesignPatterns.structural.composite.leaf;

import oopDesignPatterns.structural.composite.component.Shape;

public class Circle implements Shape {
    @Override
    public void draw(String fillColor) {
        System.out.println("Draw circle with " + fillColor + " color");
    }
}
