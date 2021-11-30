package oopDesignPatterns.structural.adapter;

import oopDesignPatterns.structural.adapter.clientInterface.AsianPlug;
import oopDesignPatterns.structural.adapter.service.EuroPlug;

public class PlugAdapter implements EuroPlug {
    AsianPlug plug;

    public PlugAdapter(AsianPlug plug) {
        this.plug = plug;
    }

    @Override
    public void connect() {
        plug.connect();
    }
}
