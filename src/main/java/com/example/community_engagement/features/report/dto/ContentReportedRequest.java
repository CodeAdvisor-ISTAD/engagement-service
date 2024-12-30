package com.example.community_engagement.features.report.dto;

import jakarta.validation.constraints.NotNull;

public record ContentReportedRequest(
        @NotNull(message = "Content ID cannot be null") String contentId,
        @NotNull(message = "Type cannot be null") String type,
        @NotNull(message = "User ID cannot be null") String userId
) {
}
