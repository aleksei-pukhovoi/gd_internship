package oopDesignPatterns.creational.factoryMethod.creator;

import oopDesignPatterns.creational.factoryMethod.product.Pizza;
import oopDesignPatterns.creational.factoryMethod.product.SeafoodPizza;

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza() {
        return new SeafoodPizza();
    }
}
