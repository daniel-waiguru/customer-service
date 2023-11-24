package com.danielwaiguru.springone.service;

import com.danielwaiguru.springone.models.Customer;
import com.danielwaiguru.springone.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    public void updateCustomer(Integer id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) return;
        Customer customer1 = optionalCustomer.get();
        customer1.setName(customer.getName());
        customer1.setEmail(customer.getEmail());
        customer1.setAge(customer.getAge());
        customerRepository.save(customer1);
    }
}
