package com.example.community_engagement.features.reaction.dto;

import jakarta.validation.constraints.NotNull;

public record ContentReactedRequest(
        @NotNull(message = "Content ID cannot be null") String contentId,
        @NotNull(message = "Type cannot be null") String type,
        @NotNull(message = "User ID cannot be null") String userId,
        @NotNull(message = "Reaction Type cannot be null") String reactionType
) {
}
