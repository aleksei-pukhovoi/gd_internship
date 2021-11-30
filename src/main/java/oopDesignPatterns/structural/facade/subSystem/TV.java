package oopDesignPatterns.structural.facade.subSystem;

public class TV implements Smart {
    @Override
    public void on() {
        System.out.println("TV is on. watch cartoons");
    }
}
