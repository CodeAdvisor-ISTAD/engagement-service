package com.example.community_engagement.features.report;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReportRepository extends MongoRepository<Report, String> {
    // Custom query methods (if needed)
    List<Report> findByUserId(String userId);
    List<Report> findByType(String dataType);
}
