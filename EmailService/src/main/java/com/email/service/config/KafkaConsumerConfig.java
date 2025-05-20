package com.email.service.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.email.service.entities.Employee;

@Configuration
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, Employee> consumerFactory() {
	    Map<String, Object> props = new HashMap<>();
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-group");
	    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

	    // Tell Spring to ignore the type info in message headers and use your local Employee class
	    return new DefaultKafkaConsumerFactory<>(
	        props,
	        new StringDeserializer(),
	        new JsonDeserializer<>(Employee.class, false) // 'false' disables headers usage
	    );
	}

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Employee> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Employee> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}

