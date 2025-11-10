package com.example.kafka.records;

import java.time.Instant;

public record WeatherRecord(
        String city,
        double temperature,
        double humidity,
        Instant timestamp
) {}

