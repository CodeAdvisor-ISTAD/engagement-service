package com.example.community_engagement.features.reply.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReplyRequest(
        @NotNull(message = "userId cannot be null") String userId,
        @NotNull(message = "body cannot be null") String body
) { }
