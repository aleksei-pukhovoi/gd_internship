package oopDesignPatterns.creational.factoryMethod;

import oopDesignPatterns.creational.factoryMethod.creator.ChicagoPizzaStore;
import oopDesignPatterns.creational.factoryMethod.creator.NYPizzaStore;
import oopDesignPatterns.creational.factoryMethod.creator.PizzaStore;
import oopDesignPatterns.creational.factoryMethod.creator.PizzaType;

public class Application {
    public PizzaStore pizzaStore;

    public void initialize(PizzaType type) {
        switch (type) {
            case MARGARITA:
                pizzaStore = new NYPizzaStore();
                break;
            case SEAFOOD:
                pizzaStore = new ChicagoPizzaStore();
        }
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.initialize(PizzaType.MARGARITA);
        app.pizzaStore.orderPizza();
        app.initialize(PizzaType.SEAFOOD);
        app.pizzaStore.orderPizza();
    }
}
