package com.sqy.metrics.service;

import java.util.*;

import com.sqy.metrics.kafka_models.Metric;
import com.sqy.metrics.repository.MetricsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {
    private static final Logger logger = LoggerFactory.getLogger(MetricsService.class);
    private final MetricsRepository metricsRepository;

    public MetricsService(MetricsRepository metricsRepository) { this.metricsRepository = metricsRepository; }

    public ResponseEntity<Metric> findById(long id) {
        return ResponseEntity.of(metricsRepository.findById(id).map(MetricsMapper::fromModel));
    }

    public ResponseEntity<List<Metric>> findAll() {
        return ResponseEntity.ok(metricsRepository.findAll().stream().map(MetricsMapper::fromModel).toList());
    }

    @KafkaListener(topics = "metrics_consumer")
    public void listen(Metric metric) {
        logger.info("Receive new metric: {}", metric);
        metricsRepository.save(MetricsMapper.toModel(metric));
    }
}
