package com.example.springDBDocker.controller;

import com.example.springDBDocker.dto.CreateCustomerDto;
import com.example.springDBDocker.model.Customer;
import com.example.springDBDocker.service.CustomerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class CustomerController {

    private final CustomerService customerService;
    private final EmployeeModelAssembler assembler;

    public CustomerController(CustomerService customerService, EmployeeModelAssembler assembler) {
        this.customerService = customerService;
        this.assembler = assembler;
    }
/*
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        List<Customer> allCustomers = customerService.getAllCustomers();
        if (allCustomers.isEmpty()){
            System.out.println("EMPTY");
        } else{
            System.out.println(allCustomers.size());
        }
        allCustomers.forEach(customer->System.out.println(customer.toString()+" : "+customer.getId()));
        return ResponseEntity.ok(allCustomers);
        //return CollectionModel.of(allCustomers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
    }
*/
    /*
    @GetMapping("/customers/{id}")
    public EntityModel<Customer> getCustomer(@PathVariable(value = "id") Long customerId) throws Exception {
        Customer customer = customerService.findById(customerId);
        return EntityModel.of(customer, //
                linkTo(methodOn(CustomerController.class).one(customerId)).withSelfRel(),
                linkTo(methodOn(CustomerController.class).all()).withRel("customers"));
    }
*/
    @PostMapping("/create")
    public ResponseEntity<CreateCustomerDto> createCustomer(CreateCustomerDto dto) {
        customerService.createCustomer(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/customers/{id}")
    EntityModel<Customer> one(@PathVariable Long id) throws Exception {

        Customer customer = customerService.findById(id);

        return assembler.toModel(customer);
    }

    @GetMapping("/customers")
    public CollectionModel<EntityModel<Customer>> all() {
        List<EntityModel<Customer>> allCustomers = customerService.getAllCustomers().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        allCustomers.forEach(customer->System.out.println(customer.toString()+" : "+customer.getContent().getId()));
        //return ResponseEntity.ok(allCustomers);
        return CollectionModel.of(allCustomers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
    }
}