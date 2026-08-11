package com.omsai.spring_7_rest_mvc.mappers;

import com.omsai.spring_7_rest_mvc.entities.Customer;
import com.omsai.spring_7_rest_mvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDto(Customer customer);
}

