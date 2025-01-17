package com.example.community_engagement.features.report;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    // Create a new report
    Report createReport(Report report, String contentId, String commentId);

    // Get all reports
    List<Report> getAllReports();


    // Delete a report by ID
    void deleteReport(String reportId);

    // Find reports by type
    List<Report> getReportsByType(String type);

    // Find reports by userId
    List<Report> getReportsByUserId(String userId);
}
