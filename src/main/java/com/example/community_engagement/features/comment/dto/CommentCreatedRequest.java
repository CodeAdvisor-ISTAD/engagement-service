package com.example.community_engagement.features.comment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CommentCreatedRequest(
        @JsonProperty("userId") String userId,
        @JsonProperty("type") String type,
        @JsonProperty("contentId") String contentId,
        @JsonProperty("body") String body,
        @JsonProperty("ownerId") String ownerId, // New field
        @JsonProperty("slug") String slug
) {}