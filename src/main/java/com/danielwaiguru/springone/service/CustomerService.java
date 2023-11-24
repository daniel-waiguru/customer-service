package com.danielwaiguru.springone.service;

import com.danielwaiguru.springone.models.Customer;
import com.danielwaiguru.springone.models.requests.CustomerRequest;
import com.danielwaiguru.springone.repositories.CustomerRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public String saveCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.name());
        customer.setEmail(customerRequest.email());
        customer.setAge(customerRequest.age());
        Optional<Customer> existingCustomer = customerRepository
                .findCustomerByEmailEqualsIgnoreCase(customerRequest.email());
        if (existingCustomer.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email already used!");
        }
        return customerRepository.save(customer).getName();
    }

    public void deleteCustomer(Integer id) {
        boolean customerExists = customerRepository.existsById(id);
        if (!customerExists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        customerRepository.deleteById(id);
    }

    public void updateCustomer(Integer id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        Customer customer1 = optionalCustomer.get();
        customer1.setName(customer.getName());
        customer1.setEmail(customer.getEmail());
        customer1.setAge(customer.getAge());
        customerRepository.save(customer1);
    }
}
