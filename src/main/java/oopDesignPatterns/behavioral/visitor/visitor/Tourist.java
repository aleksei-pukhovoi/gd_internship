package oopDesignPatterns.behavioral.visitor.visitor;

import oopDesignPatterns.behavioral.visitor.element.Cinema;
import oopDesignPatterns.behavioral.visitor.element.Theatre;

public class Tourist implements Visitor {
    @Override
    public void visit(Cinema cinema) {
        System.out.println("Watching Russian films");
    }

    @Override
    public void visit(Theatre theatre) {
        System.out.println("Watching Shchelkunchik ballet");
    }
}
