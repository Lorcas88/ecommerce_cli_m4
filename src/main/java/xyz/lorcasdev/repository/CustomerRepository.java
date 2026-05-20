package xyz.lorcasdev.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.lorcasdev.exception.ElementNotFoundException;
import xyz.lorcasdev.model.Customer;

public class CustomerRepository {

    private final Map<Integer, Customer> customers = new HashMap<>();
    private int counter = 1;

    public Customer save(String name, String lastName, String email, String phone, String companyName, String address, LocalDateTime createdAt) {
        int id = counter++;
        Customer customer = new Customer(id, name, lastName, email, phone, companyName, address, createdAt);
        customers.put(id, customer);
        return customer;
    }

    public Customer findById(int id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new ElementNotFoundException(id);
        }
        return customer;
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    public Customer update(Customer customer) {
        customers.put(customer.getId(), customer);
        return customer;
    }

    public void delete(int id) {
        customers.remove(id);
    }
}
