package com.example.springDBDocker.repository;

import com.example.springDBDocker.dto.CreateCustomerDto;
import com.example.springDBDocker.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CustomerService customerService;

    public DataInitializer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("INITIALIZE");
        CreateCustomerDto chef1 = new CreateCustomerDto("Gordon","Ramsay");
        CreateCustomerDto chef2 = new CreateCustomerDto("Jamie","Oliver");
        CreateCustomerDto chef3 = new CreateCustomerDto("Anthony","Bourdain");

        customerService.createCustomer(chef1);
        customerService.createCustomer(chef2);
        customerService.createCustomer(chef3);

    }
}
