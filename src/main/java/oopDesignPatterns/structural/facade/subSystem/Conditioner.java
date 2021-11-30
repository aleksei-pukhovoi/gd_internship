package oopDesignPatterns.structural.facade.subSystem;

public class Conditioner implements Smart {
    @Override
    public void on() {
        System.out.println("Сonditioner is on. Temperature is normal");
    }
}
