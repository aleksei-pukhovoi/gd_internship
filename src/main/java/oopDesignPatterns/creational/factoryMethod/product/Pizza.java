package oopDesignPatterns.creational.factoryMethod.product;

public interface Pizza {
    void prepare();
    void bake();
    default void slice(){
        System.out.println("slicing");
    }
    void pack();
}
