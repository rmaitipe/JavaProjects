package com.example.springDBDocker.serviceimpl;

import com.example.springDBDocker.controller.ResourceNotFoundException;
import com.example.springDBDocker.dto.CreateCustomerDto;
import com.example.springDBDocker.model.Customer;
import com.example.springDBDocker.repository.CustomerRepository;
import com.example.springDBDocker.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        System.out.println("getAllCustomers Start");
        return customerRepository.findAll();
    }

    @Override
    public void createCustomer(CreateCustomerDto dto) {
        System.out.println("SAVE Start");
        if (ObjectUtils.isEmpty(dto)) {
            throw new IllegalArgumentException("customer is empty");
        }
        //Customer customer = new Customer(dto.getFirstName(), dto.getLastName());
        Customer customer = Customer.builder().firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
        customerRepository.save(customer);
        System.out.println("SAVE End");
    }

    @Override
    public Customer findById(Long customerId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
        return customer;
    }
}