package oopDesignPatterns.structural.facade.subSystem;

public class Conditioner implements Smart {
    @Override
    public void on() {
        System.out.println("–°onditioner is on. Temperature is normal");
    }
}
