package xyz.lorcasdev.service;

import java.time.LocalDateTime;
import java.util.List;

import xyz.lorcasdev.model.Customer;
import xyz.lorcasdev.repository.CustomerRepository;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(String name, String lastName, String email, String phone, String companyName, String address) {
        // Asignamos la fecha de creación automáticamente al momento de registro
        LocalDateTime createdAt = LocalDateTime.now();
        return customerRepository.save(name, lastName, email, phone, companyName, address, createdAt);
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
