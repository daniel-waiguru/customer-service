package com.danielwaiguru.springone.repositories;

import com.danielwaiguru.springone.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
