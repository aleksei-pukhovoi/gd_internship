package oopDesignPatterns.behavioral.visitor.visitor;

import oopDesignPatterns.behavioral.visitor.element.Cinema;
import oopDesignPatterns.behavioral.visitor.element.Theatre;

public class Student implements Visitor {
    @Override
    public void visit(Cinema cinema) {
        System.out.println("Watching Space Wars");
    }

    @Override
    public void visit(Theatre theatre) {
        System.out.println("It's good place for sleeping");
    }
}
