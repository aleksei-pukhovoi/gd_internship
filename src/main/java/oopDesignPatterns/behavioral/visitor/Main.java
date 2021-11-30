package oopDesignPatterns.behavioral.visitor;

import oopDesignPatterns.behavioral.visitor.element.Cinema;
import oopDesignPatterns.behavioral.visitor.element.EntertainmentPlace;
import oopDesignPatterns.behavioral.visitor.element.Theatre;
import oopDesignPatterns.behavioral.visitor.visitor.Student;
import oopDesignPatterns.behavioral.visitor.visitor.Tourist;
import oopDesignPatterns.behavioral.visitor.visitor.Visitor;

public class Main {
    public static void main(String[] args) {
        Visitor student = new Student();
        Visitor tourist = new Tourist();
        EntertainmentPlace cinema = new Cinema();
        EntertainmentPlace theatre = new Theatre();
        cinema.accept(student);
        cinema.accept(tourist);

        theatre.accept(student);
        theatre.accept(tourist);
    }
}
