package oopDesignPatterns.creational.factoryMethod.creator;

import oopDesignPatterns.creational.factoryMethod.product.Pizza;

public abstract class PizzaStore {
    public abstract Pizza createPizza();

    public void orderPizza() {
        Pizza pizza = createPizza();
        pizza.prepare();
        pizza.bake();
        pizza.slice();
        pizza.pack();
    }
}
