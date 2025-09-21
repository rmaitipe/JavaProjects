package com.example.springDBDocker.config;

/*
@Configuration
public class KafkaProducerMultiTypeConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    //@Value("${spring.kafka.topic.kafka-topic}")
    //private String topicName;

    @Bean
    public ProducerFactory<String, Object> multiTypeProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(JsonSerializer.TYPE_MAPPINGS,
                "greeting:com.example.springDBDocker.service.Greeting, farewell:com.example.springDBDocker.service.Farewell");
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaMultiTypeTemplate() {
        return new KafkaTemplate<>(multiTypeProducerFactory());
    }
}

 */