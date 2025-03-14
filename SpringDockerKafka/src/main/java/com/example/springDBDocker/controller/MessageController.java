package com.example.springDBDocker.controller;

import com.example.springDBDocker.service.MessageRequestDTO;
import com.example.springDBDocker.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/kafka-fundamental")
public class MessageController {

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    MessageService messageService;

    @PostMapping("/send-message")
    public ResponseEntity<Object> publish(@RequestBody MessageRequestDTO request) {
        messageService.publish(request);

        return ResponseEntity.ok("Message sent");
    }
}