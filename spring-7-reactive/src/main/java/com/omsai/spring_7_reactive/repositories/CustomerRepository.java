package com.omsai.spring_7_reactive.repositories;

import com.omsai.spring_7_reactive.domain.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}