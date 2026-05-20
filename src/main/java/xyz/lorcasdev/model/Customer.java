package xyz.lorcasdev.model;

import java.time.LocalDateTime;

public class Customer {

    private final int id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String companyName;
    private String address;
    private final LocalDateTime createdAt;

    // Constructor
    public Customer(int id, String name, String lastName, String email, String phone, String companyName, String address, LocalDateTime createdAt) {
        Validator.notNegative(id, "id");
        Validator.notEmpty(name, "nombre");
        Validator.notEmpty(lastName, "apellido");
        Validator.notEmpty(email, "email");
        Validator.notNull(createdAt, "fecha de creación");

        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.companyName = companyName;
        this.address = address;
        this.createdAt = createdAt;
    }

    // Setters
    public void setName(String name) {
        Validator.notEmpty(name, "nombre");
        this.name = name;
    }

    public void setLastName(String lastName) {
        Validator.notEmpty(lastName, "apellido");
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        Validator.notEmpty(email, "email");
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s %s | %s", id, name, lastName, email);
    }
}
