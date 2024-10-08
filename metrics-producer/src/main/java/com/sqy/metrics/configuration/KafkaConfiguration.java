package com.sqy.metrics.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {
    @Bean
    public NewTopic orderPlacedTopic() {
        return TopicBuilder.name("metrics_consumer")
            .partitions(1)
            .build();
    }
}
