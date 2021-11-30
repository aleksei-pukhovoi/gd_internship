package oopDesignPatterns.structural.composite.composite;

import oopDesignPatterns.structural.composite.component.Shape;

import java.util.ArrayList;
import java.util.List;

public class Drawing implements Shape {
    private List<Shape> shapes = new ArrayList<>();

    @Override
    public void draw(String fillColor) {
        shapes.forEach(shape -> shape.draw(fillColor));
    }

    public void add(Shape shape) {
        shapes.add(shape);
    }

    public  void remove(Shape shape) {
        shapes.remove(shape);
    }

    public void clear(){
        System.out.println("Clear all shapes from drawing");
        shapes.clear();
    }
}
