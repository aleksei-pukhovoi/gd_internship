package oopDesignPatterns.structural.decorator.decorator;

import oopDesignPatterns.structural.decorator.component.Car;

public class CarDecorator implements Car {
    private Car car;

    public CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public void assemble() {
        this.car.assemble();
    }
}
