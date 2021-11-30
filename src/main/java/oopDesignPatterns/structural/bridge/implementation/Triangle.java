package oopDesignPatterns.structural.bridge.implementation;

import oopDesignPatterns.structural.bridge.abstraction.Color;

public class Triangle extends Shape{

    public Triangle(Color color) {
        super(color);
    }

    @Override
    public void applyColor() {
        System.out.print("Triangle filled with color ");
        color.applyColor();
    }
}
