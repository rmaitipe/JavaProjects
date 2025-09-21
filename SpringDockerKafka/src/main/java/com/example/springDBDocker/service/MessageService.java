package com.example.springDBDocker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public MessageService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    KafkaTemplate<String, String> kafkaTemplate;

    //@Autowired
    //KafkaTemplate<String, Object> kafkaMultiTypeTemplate;

    //@Autowired
    //KafkaTemplate<String, Greeting> kafkaCustomTemplate;

    public void publish(MessageRequestDTO message) {
        kafkaTemplate.send("test-topic", message.getMessage());
    }
    /*
    public void publishMultiType(Object message) {
        kafkaMultiTypeTemplate.send("multitype", message);
    }

    public void publishCustom(Greeting greeting) {
        kafkaCustomTemplate.send("customType", greeting);
    }
     */

}