package oopDesignPatterns.creational.abstractFactory.factory;

public enum FactoryType {
    ROUNDED_FACTORY{
        @Override
        public AbstractFactory getFactory() {
            return new RoundedShapeFactory();
        }
    },
    SHAPE_FACTORY{
        @Override
        public AbstractFactory getFactory() {
            return new ShapeFactory();
        }
    };

    public abstract AbstractFactory getFactory();
}
