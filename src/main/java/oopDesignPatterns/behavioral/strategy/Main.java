package oopDesignPatterns.behavioral.strategy;

import oopDesignPatterns.behavioral.strategy.context.Path;
import oopDesignPatterns.behavioral.strategy.strategy.BusStrategy;
import oopDesignPatterns.behavioral.strategy.strategy.CarStrategy;
import oopDesignPatterns.behavioral.strategy.strategy.PathStrategy;
import oopDesignPatterns.behavioral.strategy.strategy.WalkStrategy;

public class Main {
    public static void main(String[] args) {
        PathStrategy bus = new BusStrategy();
        PathStrategy car = new CarStrategy();
        PathStrategy walk = new WalkStrategy();

        Path path = new Path();

        path.setPathStrategy(bus);
        path.create();
        path.setPathStrategy(car);
        path.create();
        path.setPathStrategy(walk);
        path.create();
    }
}
