package com.omsai.spring_7_rest_mvc.service;

import com.omsai.spring_7_rest_mvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    Optional<CustomerDTO> getCustomerById(UUID id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO saveNewCustomer(CustomerDTO customerDTO);

    void updateCustomerById(UUID customerId, CustomerDTO customerDTO);

    void deleteById(UUID customerId);

    void patchCustomerById(UUID customerId, CustomerDTO customerDTO);
}
