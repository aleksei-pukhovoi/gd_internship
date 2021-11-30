package oopDesignPatterns.structural.bridge;

import oopDesignPatterns.structural.bridge.abstraction.GreenColor;
import oopDesignPatterns.structural.bridge.abstraction.RedColor;
import oopDesignPatterns.structural.bridge.implementation.Pentagon;
import oopDesignPatterns.structural.bridge.implementation.Shape;
import oopDesignPatterns.structural.bridge.implementation.Triangle;

public class Main {
    public static void main(String[] args) {
        Shape triangle = new Triangle(new RedColor());
        triangle.applyColor();

        Shape pentagon = new Pentagon(new GreenColor());
        pentagon.applyColor();
    }
}
