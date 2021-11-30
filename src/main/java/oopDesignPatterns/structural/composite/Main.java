package oopDesignPatterns.structural.composite;

import oopDesignPatterns.structural.composite.component.Shape;
import oopDesignPatterns.structural.composite.composite.Drawing;
import oopDesignPatterns.structural.composite.leaf.Circle;
import oopDesignPatterns.structural.composite.leaf.Triangle;

public class Main {
    public static void main(String[] args) {
        Shape triangle1 = new Triangle();
        Shape triangle2 = new Triangle();
        Shape circle1 = new Circle();

        Drawing drawing = new Drawing();
        drawing.add(triangle1);
        drawing.add(triangle2);
        drawing.add(circle1);
        drawing.draw("Red");
        drawing.clear();
        drawing.add(triangle1);
        drawing.add(circle1);
        drawing.draw("Green");
    }
}
