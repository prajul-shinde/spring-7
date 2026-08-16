package com.omsai.spring_7_reactive.mapper;

import com.omsai.spring_7_reactive.domain.Customer;
import com.omsai.spring_7_reactive.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);
}