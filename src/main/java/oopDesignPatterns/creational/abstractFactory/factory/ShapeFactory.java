package oopDesignPatterns.creational.abstractFactory.factory;

import oopDesignPatterns.creational.abstractFactory.product.*;

public class ShapeFactory implements AbstractFactory {
    @Override
    public Shape createShape(ShapeType type) {
        return type.getShape();
    }
}
