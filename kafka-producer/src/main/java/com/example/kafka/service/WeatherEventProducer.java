package com.example.kafka.service;

import com.example.kafka.records.WeatherRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class WeatherEventProducer {

    private final KafkaTemplate<String, WeatherRecord> kafkaTemplate;

    private static final int NUM_PARTITIONS = 6;

    public WeatherEventProducer(KafkaTemplate<String, WeatherRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendWeatherUpdate(WeatherRecord weatherRecord) {
        int hash = weatherRecord.city().hashCode();
        int partition = Math.abs(hash % NUM_PARTITIONS);

        kafkaTemplate.send("weather-updates-1", weatherRecord.city(), weatherRecord);

        System.out.println("🌦️ Sent: " + weatherRecord.toString());
    }


}
