package com.sqy.metrics.service;

import com.sqy.metrics.kafka_models.Metric;
import com.sqy.metrics.doimain.MetricModel;

public class MetricsMapper {
    public static Metric fromModel(MetricModel metricModel) {
        return new Metric(metricModel.getName(), metricModel.getValue());
    }

    public static MetricModel toModel(Metric metric) {
        return new MetricModel(null, metric.getName(), metric.getValue());
    }
}
