package com.example.springDBDocker.config;

/*
@Configuration
@EnableKafka
public class KafkaConsumerMultiTypeConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;

    @Bean
    public ConsumerFactory<String, Object> multiTypeConsumerFactory() {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> multiTypeKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(multiTypeConsumerFactory());
        factory.setRecordMessageConverter(multiTypeConverter());
        return factory;
    }

    @Bean
    public RecordMessageConverter multiTypeConverter() {
        StringJsonMessageConverter converter = new StringJsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
        typeMapper.addTrustedPackages("com.example.springDBDocker");
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("greeting", Greeting.class);
        mappings.put("farewell", Farewell.class);
        typeMapper.setIdClassMapping(mappings);
        converter.setTypeMapper(typeMapper);
        return converter;
    }
}

 */