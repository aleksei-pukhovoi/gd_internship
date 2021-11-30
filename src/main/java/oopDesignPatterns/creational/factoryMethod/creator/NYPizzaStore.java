package oopDesignPatterns.creational.factoryMethod.creator;

import oopDesignPatterns.creational.factoryMethod.product.Margarita;
import oopDesignPatterns.creational.factoryMethod.product.Pizza;

public class NYPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza() {
        return new Margarita();
    }
}
