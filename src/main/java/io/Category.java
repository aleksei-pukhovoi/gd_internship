package io;

public enum Category {
    A,
    B,
    C;

    public static Category getCategory(int index) {
        switch (index) {
            case 1:
                return A;
            case 2:
                return B;
            default:
                return C;
        }
    }
}
