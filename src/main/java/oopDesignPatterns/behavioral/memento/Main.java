package oopDesignPatterns.behavioral.memento;

import oopDesignPatterns.behavioral.memento.caretaker.CareTaker;
import oopDesignPatterns.behavioral.memento.memento.Memento;
import oopDesignPatterns.behavioral.memento.originator.TextFile;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        TextFile textFile = new TextFile("1.0","book", 100, LocalDateTime.now());
        System.out.println(textFile);
        Memento memento = textFile.getMemento();

        CareTaker careTaker = new CareTaker();

        careTaker.addMemento(memento.getVersion(), memento);
        Memento memento1 = textFile.getMemento();
        memento1.setVersion("1.1");
        memento1.setWords(1000);
        memento1.setDate(LocalDateTime.now().plusDays(2));
        textFile.setMemento(memento1);
        System.out.println(textFile);
        careTaker.addMemento(memento1.getVersion(),memento1);
        textFile.setMemento(careTaker.getMemento("1.0"));
        System.out.println(textFile);
    }
}
