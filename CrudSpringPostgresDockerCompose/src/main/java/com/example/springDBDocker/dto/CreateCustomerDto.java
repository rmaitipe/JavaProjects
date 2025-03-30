package com.example.springDBDocker.dto;

import lombok.Data;

@Data
public class CreateCustomerDto {

    private String firstName;

    private String lastName;

    public CreateCustomerDto(String first, String last) {
        this.firstName=first;
        this.lastName=last;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}