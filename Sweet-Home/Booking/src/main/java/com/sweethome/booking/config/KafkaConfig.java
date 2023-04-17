package com.sweethome.booking.config;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * This class contains configuration related to Kafka server and produces the Producer object
 */
@Configuration
public class KafkaConfig {
	
	@Value("${bootstrap.servers}")
	private String bootstrapServer;
	private Properties properties;
	
	public Properties loadProducerConfig() {
		properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServer);
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("linger.ms", 0);
        properties.put("partitioner.class", "org.apache.kafka.clients.producer.internals.DefaultPartitioner");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("request.timeout.ms", 30000);
        properties.put("timeout.ms", 30000);
        properties.put("max.in.flight.requests.per.connection", 5);
        properties.put("retry.backoff.ms", 5);
		return properties;
	}
	
	@Bean
	public  Producer<String, String> createProducer() {
		loadProducerConfig();
		return new KafkaProducer<>(properties);
	}

}
