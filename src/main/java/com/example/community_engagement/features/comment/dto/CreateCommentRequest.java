package com.example.community_engagement.features.comment.dto;

import jakarta.validation.constraints.NotNull;

public record CreateCommentRequest(
        @NotNull(message = "userId cannot be null") String userId,
        @NotNull(message = "contentId cannot be null") String contentId,
        @NotNull(message = "body cannot be null") String body
) {}