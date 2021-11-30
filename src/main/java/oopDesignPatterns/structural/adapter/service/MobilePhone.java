package oopDesignPatterns.structural.adapter.service;

public class MobilePhone {
    public void charge(EuroPlug euroPlug) {
        euroPlug.connect();
    }
}
