package oopDesignPatterns.structural.bridge.implementation;

import oopDesignPatterns.structural.bridge.abstraction.Color;

public abstract class Shape {

    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract void applyColor();
}
