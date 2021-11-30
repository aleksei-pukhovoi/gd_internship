package oopDesignPatterns.creational.factoryMethod.product;

import oopDesignPatterns.creational.factoryMethod.product.Pizza;

public class SeafoodPizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("preparing ingredients: anchovy, scallops, basil");
    }

    @Override
    public void bake() {
        System.out.println("baking for 10 min");
    }

    @Override
    public void pack() {
        System.out.println("packing SeafoodPizza");
    }
}
