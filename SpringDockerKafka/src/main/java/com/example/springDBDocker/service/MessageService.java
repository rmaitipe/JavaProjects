package com.example.springDBDocker.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public MessageService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    KafkaTemplate<String, String> kafkaTemplate;

    public void publish(MessageRequestDTO message) {
        kafkaTemplate.send("test-topic", message.getMessage());
    }
}