package com.sqy.metrics;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import com.sqy.metrics.kafka_models.Metric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MetricProducer {
    private static final Logger logger = LoggerFactory.getLogger(MetricProducer.class);
    private static final List<String> metricNames = List.of(
        "METHOD_CALL_COUNT", "AVG_TIME", "SUCCESSFUL_CALL_PERCENTAGE", "TOTAL_TIME");
    private final KafkaTemplate<String, Metric> metricKafkaTemplate;

    public MetricProducer(KafkaTemplate<String, Metric> metricKafkaTemplate) {
        this.metricKafkaTemplate = metricKafkaTemplate;
    }

    @Async
    @Scheduled(fixedRate = 10000)
    public void generateMetric() {
        Metric metric = generateRandomMetric();
        logger.info("Sending metric: {}", metric);
        metricKafkaTemplate.send("metrics_consumer", metric);
    }

    private static Metric generateRandomMetric() {
        int idx = ThreadLocalRandom.current().nextInt(metricNames.size());
        double value = ThreadLocalRandom.current().nextDouble();
        return new Metric(metricNames.get(idx), value);
    }
}
