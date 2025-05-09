package com.example.springDBDocker.service;

import com.example.springDBDocker.model.Customer;
import com.example.springDBDocker.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final CustomerRepository customerRepository;

    public DataLoader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostConstruct
    public void init() {
        customerRepository.save(new Customer("Gozde", "Yalcin"));
        customerRepository.save(new Customer("Boncuk", "Yestlin"));
    }
}