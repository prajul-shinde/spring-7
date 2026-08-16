package com.omsai.spring_7_reactive_mongo.mappers;

import com.omsai.spring_7_reactive_mongo.domain.Customer;
import com.omsai.spring_7_reactive_mongo.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDTO customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}