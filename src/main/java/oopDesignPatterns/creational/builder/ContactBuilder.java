package oopDesignPatterns.creational.builder;

public class ContactBuilder {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;

    public ContactBuilder name(String name) {
        this.name = name;
        return this;
    }
    public ContactBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }
    public ContactBuilder email(String email) {
        this.email = email;
        return this;
    }
    public ContactBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }
    public ContactBuilder address(String address) {
        this.address = address;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Contact build() {
        return new Contact(this);
    }
}
