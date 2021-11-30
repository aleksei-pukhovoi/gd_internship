package oopDesignPatterns.structural.decorator.decorator;

import oopDesignPatterns.structural.decorator.component.Car;

public class LuxuryCar extends CarDecorator {
    public LuxuryCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.println("Adding features of Luxury Car");
    }
}
