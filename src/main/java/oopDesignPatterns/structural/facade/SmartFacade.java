package oopDesignPatterns.structural.facade;

import oopDesignPatterns.structural.facade.subSystem.*;

import java.util.ArrayList;
import java.util.List;

public class SmartFacade {
   private final List<Smart> list  = new ArrayList<>();
    {
        list.add(new TV());
        list.add(new Conditioner());
        list.add(new Kettle());
        list.add(new Light());
    }

    public void start() {
        list.forEach(Smart::on);
    }
}
