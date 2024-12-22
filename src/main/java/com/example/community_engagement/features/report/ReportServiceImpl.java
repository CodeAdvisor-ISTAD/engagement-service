package com.example.community_engagement.features.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final ReportRepository reportRepository;

    @Override
    public Report createReport(Report report) {
        report.setCreatedAt(LocalDateTime.now());  // Ensure createdAt is set to the current time
        return reportRepository.save(report);
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public void deleteReport(String reportId) {
        Optional<Report> reportOpt = reportRepository.findById(reportId);

        if (reportOpt.isPresent()) {
            reportRepository.deleteById(reportId);
        } else {
            throw new RuntimeException("Report not found with id: " + reportId);
        }
    }

    @Override
    public List<Report> getReportsByUserId(String userId) {
        return reportRepository.findByUserId(userId);
    }

    public List<Report> getReportsByType(String dataType) {
        return reportRepository.findByDataType(dataType);
    }
}
