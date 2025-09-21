package com.example.springDBDocker.controller;

import com.example.springDBDocker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    //@Autowired
    //GreetingService greetingService;


    @PostMapping("/send-message")
    public ResponseEntity<Object> publish(@RequestBody MessageRequestDTO request) {
        messageService.publish(request);
        return ResponseEntity.ok("Message sent");
    }
/*
    @PostMapping("/send-greeting")
    public ResponseEntity<Object> publish(@RequestBody Greeting greeting) {
        greetingService.publish(greeting);
        messageService.publishMultiType(greeting);
        return ResponseEntity.ok("Message sent");
    }

    @PostMapping("/send-farewell")
    public ResponseEntity<Object> publish(@RequestBody Farewell farewell) {
        messageService.publishMultiType(farewell);
        return ResponseEntity.ok("Message sent");
    }
*/
}