package com.danielwaiguru.springone.repositories;

import com.danielwaiguru.springone.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerByEmailEqualsIgnoreCase(String email);
}
