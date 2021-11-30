package oopDesignPatterns.behavioral.memento.caretaker;

import oopDesignPatterns.behavioral.memento.memento.Memento;

import java.util.HashMap;
import java.util.Map;

public class CareTaker {
    Map<String, Memento> mementoMap = new HashMap<>();

    public void addMemento(String version, Memento memento) {
            mementoMap.put(version, memento);
    }

    public Memento getMemento(String version) {
        return mementoMap.get(version);
    }
 }
