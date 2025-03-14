package com.example.springDBDocker.Listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics = "test-topic",
            groupId = "test-consumer-group")
    public void listener(String message) {
        System.out.println("Received message: " + message + " :D");
    }
}
