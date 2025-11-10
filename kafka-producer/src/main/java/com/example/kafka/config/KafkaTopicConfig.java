package com.example.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic weatherUpdatesTopic() {
        return TopicBuilder.name("weather-updates-1")
                .partitions(6)
                .replicas(1)
                .build();
    }
}

