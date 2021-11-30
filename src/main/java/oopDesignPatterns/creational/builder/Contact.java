package oopDesignPatterns.creational.builder;

public class Contact {

    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final String address;

    Contact(ContactBuilder contactBuilder) {
        this.name = contactBuilder.getName();
        this.surname = contactBuilder.getSurname();
        this.email = contactBuilder.getEmail();
        this.phone = contactBuilder.getPhone();
        this.address = contactBuilder.getAddress();
    }
}
