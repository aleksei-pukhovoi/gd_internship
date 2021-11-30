package oopDesignPatterns.creational.abstractFactory.factory;

import oopDesignPatterns.creational.abstractFactory.product.Shape;
import oopDesignPatterns.creational.abstractFactory.product.ShapeType;

public class RoundedShapeFactory implements AbstractFactory {
    @Override
    public Shape createShape(ShapeType type) {
        return type.getShape();
    }
}
