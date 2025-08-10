package com.bhagwat.scm.inventoryService.config;

import com.bhagwat.scm.inventoryService.event.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@RefreshScope
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Configures a custom ObjectMapper for JSON serialization/deserialization.
     * This is crucial for handling polymorphic types (different event classes)
     * and ensuring proper date/time serialization.
     *
     * @return Configured ObjectMapper.
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Register JavaTimeModule for proper serialization of Java 8 Date/Time API (Instant, LocalDate, etc.)
        objectMapper.registerModule(new JavaTimeModule());
        // Disable writing dates as timestamps (e.g., "2025-08-06T10:30:00Z" instead of a long number)
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Explicitly register all event types as subtypes.
        // This tells Jackson about all the concrete classes that might be serialized
        // when the KafkaTemplate is used with 'Object' as the value type.
        objectMapper.registerSubtypes(
                new NamedType(InventoryCreatedEvent.class, "InventoryCreatedEvent"),
                new NamedType(InventoryUpdatedEvent.class, "InventoryUpdatedEvent"),
                new NamedType(InventoryDeletedEvent.class, "InventoryDeletedEvent"),
                new NamedType(SkuCreatedEvent.class, "SkuCreatedEvent"),
                new NamedType(SkuUpdatedEvent.class, "SkuUpdatedEvent"),
                new NamedType(SkuDeletedEvent.class, "SkuDeletedEvent"),
                new NamedType(ProductCreatedEvent.class, "ProductCreatedEvent"),
                new NamedType(ProductUpdatedEvent.class, "ProductUpdatedEvent"),
                new NamedType(ProductDeletedEvent.class, "ProductDeletedEvent")
                // Add any other event classes you might send
        );
        return objectMapper;
    }

    @Bean
    @RefreshScope
    public ProducerFactory<String, Object> producerFactory(ObjectMapper objectMapper) { // Inject ObjectMapper
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // This tells JsonSerializer to ADD __TypeId__ headers, which is needed by the consumer
        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, true); // Changed to true

        // Create a DefaultKafkaProducerFactory with the custom JsonSerializer
        return new DefaultKafkaProducerFactory<>(config, new StringSerializer(), new JsonSerializer<>(objectMapper));
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory(ObjectMapper objectMapper) { // Inject ObjectMapper
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "inventory-read-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // This is crucial for JsonDeserializer to trust your event packages during deserialization
        // Changed to trust all packages (*)
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        // Use the custom JsonDeserializer initialized with our ObjectMapper
        // Provide Object.class as the default type. This will be used if no __TypeId__ header is present.
        JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>(Object.class, objectMapper);

        // Pass the JsonDeserializer instance directly, so VALUE_DESERIALIZER_CLASS_CONFIG is not needed
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(ConsumerFactory<String, Object> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
