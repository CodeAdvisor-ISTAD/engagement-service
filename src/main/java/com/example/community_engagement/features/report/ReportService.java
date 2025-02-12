package com.example.community_engagement.features.report;

import com.example.community_engagement.features.share.Share;

import java.util.List;

public interface ReportService {
    // Create a new report
    Report createReport(Report report);

    // Get all reports
    List<Report> getAllReports();

    List<Report> getReportsByContentId(String contentId);

    // Delete a report by ID
    void deleteReport(String reportId);

    // Find reports by type
    List<Report> getReportsByType(String type);

    // Find reports by userId
    List<Report> getReportsByUserId(String userId);


}
