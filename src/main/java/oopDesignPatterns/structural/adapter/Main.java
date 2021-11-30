package oopDesignPatterns.structural.adapter;

import oopDesignPatterns.structural.adapter.clientInterface.AsianPlug;
import oopDesignPatterns.structural.adapter.clientInterface.SimpleAsianPlug;
import oopDesignPatterns.structural.adapter.service.EuroPlug;
import oopDesignPatterns.structural.adapter.service.MobilePhone;

public class Main {
    public static void main(String[] args) {
        AsianPlug asianPlug = new SimpleAsianPlug();
        MobilePhone phone = new MobilePhone();
        EuroPlug euroPlug = new PlugAdapter(asianPlug);
        phone.charge(euroPlug);
    }
}
