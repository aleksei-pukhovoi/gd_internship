package oopDesignPatterns.creational.builder;

public class Main {
    public static void main(String[] args) {
        Contact contact = new ContactBuilder()
                .name("Aleksei")
                .surname("Pukhovoi")
                .email("test@gmail.com")
                .phone("+71234567")
                .address("SPb")
                .build();
    }
}
