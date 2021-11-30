package oopDesignPatterns.behavioral.strategy.context;

import oopDesignPatterns.behavioral.strategy.strategy.PathStrategy;

public class Path {
    private PathStrategy pathStrategy;

    public void setPathStrategy(PathStrategy pathStrategy) {
        this.pathStrategy=pathStrategy;
    }

    public void create(){
        pathStrategy.createPath();
    }
}
