package com.danielwaiguru.springone.service;

import com.danielwaiguru.springone.models.Customer;
import com.danielwaiguru.springone.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public String saveCustomer(Customer customer) {
        return customerRepository.save(customer).getName();
    }
}
