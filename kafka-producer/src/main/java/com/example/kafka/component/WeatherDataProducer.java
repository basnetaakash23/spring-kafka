package com.example.kafka.component;

import com.example.kafka.records.WeatherRecord;
import com.example.kafka.service.WeatherEventProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Random;

@Component
public class WeatherDataProducer {

    private final WeatherEventProducer weatherEventProducer;
    private final Random random = new Random();

    private final List<String> cities = List.of("New York", "Dallas", "London", "Tokyo", "Delhi");

    public WeatherDataProducer(WeatherEventProducer weatherEventProducer) {
        this.weatherEventProducer = weatherEventProducer;
    }

    @Scheduled(fixedRate = 3000)
    public void publishWeather() {
        String city = cities.get(random.nextInt(cities.size()));
        double temp = 15 + random.nextDouble() * 20;      // 15–35°C
        double humidity = 40 + random.nextDouble() * 40;  // 40–80%

        // ✅ create record instance
        WeatherRecord weatherRecord = new WeatherRecord(city, temp, humidity, Instant.now());

        weatherEventProducer.sendWeatherUpdate(weatherRecord);
    }

}
