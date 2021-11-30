package oopDesignPatterns.creational.factoryMethod.product;

import oopDesignPatterns.creational.factoryMethod.product.Pizza;

public class Margarita implements Pizza {
    @Override
    public void prepare() {
        System.out.println("preparing ingredients: tomatoes, cheese, basil");
    }

    @Override
    public void bake() {
        System.out.println("baking for 7 min");
    }

    @Override
    public void pack() {
        System.out.println("packing Margarita");
    }
}
