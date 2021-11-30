package oopDesignPatterns.behavioral.visitor.visitor;

import oopDesignPatterns.behavioral.visitor.element.Cinema;
import oopDesignPatterns.behavioral.visitor.element.Theatre;

public interface Visitor {

    void visit(Cinema cinema);

    void visit(Theatre theatre);
}
