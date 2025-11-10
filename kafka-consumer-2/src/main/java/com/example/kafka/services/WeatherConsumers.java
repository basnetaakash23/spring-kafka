package com.example.kafka.services;

import com.example.kafka.records.WeatherRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class WeatherConsumers {

    private static final Logger logger = LoggerFactory.getLogger(WeatherConsumers.class);

    @KafkaListener(topics = "weather-updates-1", groupId = "weather-consumers")
    public void consume(WeatherRecord weatherRecord, Acknowledgment ack) {
        try {
            System.out.println("🌍 Received update: " + weatherRecord.toString());
            // process successfully (e.g., write to DB)
            ack.acknowledge(); // ✅ commit offset manually after success
            logger.info("Message received succesfully");
        } catch (RuntimeException ex) {
            logger.info("🌦️Exception occurred={}", ex.getMessage());

            // no ack → message will be redelivered
        }

    }
}
