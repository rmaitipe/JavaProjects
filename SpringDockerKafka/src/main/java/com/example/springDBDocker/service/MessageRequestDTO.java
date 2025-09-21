package com.example.springDBDocker.service;

import lombok.Data;

@Data
public class MessageRequestDTO {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
