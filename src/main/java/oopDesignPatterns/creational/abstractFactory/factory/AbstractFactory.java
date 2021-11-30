package oopDesignPatterns.creational.abstractFactory.factory;

import oopDesignPatterns.creational.abstractFactory.product.Shape;
import oopDesignPatterns.creational.abstractFactory.product.ShapeType;

public interface AbstractFactory {
    Shape createShape(ShapeType type);
}
