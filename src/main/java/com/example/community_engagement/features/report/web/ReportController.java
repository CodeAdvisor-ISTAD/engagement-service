package com.example.community_engagement.features.report.web;

import com.example.community_engagement.features.report.Report;
import com.example.community_engagement.features.report.ReportRepository;
import com.example.community_engagement.features.report.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // Create a new report
    @PostMapping
    public ResponseEntity<Report> createReport(
            @RequestBody Report report,
            @RequestParam(required = false) String contentId,
            @RequestParam(required = false) String commentId) {

        // Call the service method to create the report
        try {
            Report createdReport = reportService.createReport(report, contentId, commentId);
            return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Return a bad request response if the IDs are not valid
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Get all reports
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    // Delete a report by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable String id) {
        try {
            reportService.deleteReport(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Find reports by dataType
    @GetMapping("/type")
    public ResponseEntity<List<Report>> getReportsByType(
            @RequestParam("dataType") String dataType) {

        // Fetch reports by type from the service
        List<Report> reports = reportService.getReportsByType(dataType);

        // If reports are found, return them with a 200 status code
        if (!reports.isEmpty()) {
            return new ResponseEntity<>(reports, HttpStatus.OK);
        }

        // If no reports are found, return a 404 status code
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Find reports by userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Report>> getReportsByUserId(@PathVariable String userId) {
        List<Report> reports = reportService.getReportsByUserId(userId);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }
}
