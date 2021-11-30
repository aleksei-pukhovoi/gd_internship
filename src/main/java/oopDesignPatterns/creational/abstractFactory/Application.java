package oopDesignPatterns.creational.abstractFactory;

import oopDesignPatterns.creational.abstractFactory.factory.AbstractFactory;
import oopDesignPatterns.creational.abstractFactory.factory.FactoryProducer;
import oopDesignPatterns.creational.abstractFactory.factory.FactoryType;
import oopDesignPatterns.creational.abstractFactory.product.Shape;
import oopDesignPatterns.creational.abstractFactory.product.ShapeType;

public class Application {
    public static void main(String[] args) {
        AbstractFactory factory = FactoryProducer.getFactory(FactoryType.ROUNDED_FACTORY);
        Shape shape = factory.createShape(ShapeType.ROUNDED_RECTANGLE);
        shape.draw();
    }
}
