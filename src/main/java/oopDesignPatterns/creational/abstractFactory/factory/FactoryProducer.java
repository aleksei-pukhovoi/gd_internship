package oopDesignPatterns.creational.abstractFactory.factory;

public class FactoryProducer {
    public static AbstractFactory getFactory(FactoryType factoryType) {
        return factoryType.getFactory();
    }
}
