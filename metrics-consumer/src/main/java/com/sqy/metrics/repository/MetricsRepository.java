package com.sqy.metrics.repository;

import com.sqy.metrics.doimain.MetricModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsRepository extends JpaRepository<MetricModel, Long> {
}
