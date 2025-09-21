package com.example.springDBDocker.Listener;

import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "test-topic", groupId = "${spring.kafka.consumer.group-id}")
    public void listener(String message) {
        System.out.println("Received message: " + message + " :D");
    }

    /*  Adding Message Filter for Listeners
    @KafkaListener(topics = "topicName", containerFactory = "filterKafkaListenerContainerFactory")
    public void listenWithFilter(String message) {
        System.out.println("Received Message in filtered listener: " + message);
    }
     */
}
