package com.sdewa.account.mapper;

import com.sdewa.account.dtos.CustomerDto;
import com.sdewa.account.entity.Customer;

public class CustomerMapper {

    public  static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto){
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return  customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer){
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return  customer;
    }
}
