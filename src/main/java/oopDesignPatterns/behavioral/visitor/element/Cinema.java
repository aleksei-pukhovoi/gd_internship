package oopDesignPatterns.behavioral.visitor.element;

import oopDesignPatterns.behavioral.visitor.visitor.Visitor;

public class Cinema implements EntertainmentPlace {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
