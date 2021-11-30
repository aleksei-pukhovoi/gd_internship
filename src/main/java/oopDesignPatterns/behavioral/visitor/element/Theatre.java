package oopDesignPatterns.behavioral.visitor.element;

import oopDesignPatterns.behavioral.visitor.visitor.Visitor;

public class Theatre implements EntertainmentPlace {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
