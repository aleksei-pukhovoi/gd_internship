package oopDesignPatterns.structural.adapter.clientInterface;

public class SimpleAsianPlug implements AsianPlug {
    @Override
    public void connect() {
        System.out.println("get plug with 3 connectors");
    }
}
