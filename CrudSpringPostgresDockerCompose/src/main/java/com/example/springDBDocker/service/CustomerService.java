package com.example.springDBDocker.service;

import com.example.springDBDocker.controller.ResourceNotFoundException;
import com.example.springDBDocker.dto.CreateCustomerDto;
import com.example.springDBDocker.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    void createCustomer(CreateCustomerDto dto);
    Customer findById(Long customerId) throws ResourceNotFoundException;
}