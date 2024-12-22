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
    public ResponseEntity<Report> createReport(@Valid @RequestBody Report report) {
        Report createdReport = reportService.createReport(report);
        return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
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
    @GetMapping("/data/{type}")
    public ResponseEntity<List<Report>> getReportsByDataId(@PathVariable String type) {
        List<Report> reports = reportService.getReportsByType(type);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    // Find reports by userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Report>> getReportsByUserId(@PathVariable String userId) {
        List<Report> reports = reportService.getReportsByUserId(userId);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }
}
