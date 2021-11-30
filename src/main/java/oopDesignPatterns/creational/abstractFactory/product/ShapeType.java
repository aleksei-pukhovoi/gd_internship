package oopDesignPatterns.creational.abstractFactory.product;

public enum ShapeType {
    RECTANGLE{
        @Override
        public Shape getShape() {
            return new Rectangle();
        }
    },
    TRIANGLE{
        @Override
        public Shape getShape() {
            return new Triangle();
        }
    },
    ROUNDED_RECTANGLE{
        @Override
        public Shape getShape() {
            return new RoundedRectangle();
        }
    },
    ROUNDED_TRIANGLE{
        @Override
        public Shape getShape() {
            return new RoundedTriangle();
        }
    };

    public abstract Shape getShape();
}
