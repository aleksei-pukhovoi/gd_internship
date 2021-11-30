package oopDesignPatterns.structural.decorator;

import oopDesignPatterns.structural.decorator.component.BasicCar;
import oopDesignPatterns.structural.decorator.component.Car;
import oopDesignPatterns.structural.decorator.decorator.LuxuryCar;
import oopDesignPatterns.structural.decorator.decorator.SportsCar;

public class Main {
    public static void main(String[] args) {
        Car car = new BasicCar();
        Car sportsCar = new SportsCar(car);
        sportsCar.assemble();

        Car luxuryCar = new LuxuryCar(car);
        Car sportsLuxuryCar = new SportsCar(luxuryCar);
        sportsLuxuryCar.assemble();
    }
}
