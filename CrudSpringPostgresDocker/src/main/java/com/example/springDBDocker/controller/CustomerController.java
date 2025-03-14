package com.example.springDBDocker.controller;

import com.example.springDBDocker.dto.CreateCustomerDto;
import com.example.springDBDocker.model.Customer;
import com.example.springDBDocker.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomer() {
        List<Customer> allCustomers = customerService.getAllCustomers();
        if (allCustomers.isEmpty()){
            System.out.println("EMPTY");
        } else{
            System.out.println(allCustomers.size());
        }
        allCustomers.forEach(customer->System.out.println(customer.toString()+" : "+customer.getId()));
        //return ResponseEntity.ok(allCustomers);
        return allCustomers;
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") Long customerId) throws Exception {
        Customer customer = customerService.findById(customerId);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateCustomerDto> createCustomer(CreateCustomerDto dto) {
        customerService.createCustomer(dto);
        return ResponseEntity.ok(dto);
    }
}