package com.example.community_engagement.features.report;

import com.example.community_engagement.config.producer.CommentReportedEvent;
import com.example.community_engagement.config.producer.ContentReportedEvent;
import com.example.community_engagement.features.comment.Comment;
import com.example.community_engagement.features.comment.CommentRepository;
import com.example.community_engagement.features.report.dto.CommentReportedRequest;
import com.example.community_engagement.features.report.dto.ContentReportedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final ReportRepository reportRepository;
    private final ContentReportedEvent contentReportedEvent;
    private final CommentReportedEvent commentReportedEvent;
    @Override
    public Report createReport(Report report, String contentId, String commentId) {
        if (contentId != null) {
            // Set the contentId and dataType to "content" for content-related reports
            report.setContentId(contentId);
            report.setType("CONTENT");

            // Send content reported event to Kafka
            ContentReportedRequest event = new ContentReportedRequest(
                    report.getContentId(),
                    "CONTENT",
                    report.getUserId()
            );
            contentReportedEvent.sendContentReportedEvent("content-reported-events-topic", event);
        } else if (commentId != null) {
            // Set the commentId and dataType to "comment" for comment-related reports
            report.setCommentId(commentId);
            report.setType("COMMENT");

            // Send comment reported event to Kafka
            CommentReportedRequest event = new CommentReportedRequest(
                    report.getContentId(),  // If contentId is needed for comment reports
                    "COMMENT",
                    commentId,
                    report.getUserId()
            );
            commentReportedEvent.sendCommentReportedEvent("comment-reported-events-topic", event);
        } else {
            throw new IllegalArgumentException("Either contentId or commentId must be provided.");
        }

        // Ensure the report has the required fields: userId and reason
        StringBuilder missingFields = new StringBuilder();

        if (report.getUserId() == null || report.getUserId().isEmpty()) {
            missingFields.append("userId ");
        }

        if (report.getReason() == null || report.getReason().isEmpty()) {
            missingFields.append("reason ");
        }

        if (!missingFields.isEmpty()) {
            throw new IllegalArgumentException("Missing required fields: " + missingFields.toString().trim());
        }

        // Set the creation time and other properties
        report.setStatus("pending");
        report.setIsDeleted(false);
        report.setCreatedAt(LocalDateTime.now());

        // Save the report
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
        return reportRepository.findByType(dataType);
    }
}
