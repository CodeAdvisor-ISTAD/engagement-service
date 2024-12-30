package com.example.community_engagement.features.reply.dto;

import jakarta.validation.constraints.NotNull;

public record CommentRepliedRequest(
        @NotNull(message = "ContentId cannot be null") String contentId,
        @NotNull(message = "Type cannot be null") String type,
        @NotNull(message = "CommentId cannot be null") String commentId,
        @NotNull(message = "UserId cannot be null") String userId) {
}