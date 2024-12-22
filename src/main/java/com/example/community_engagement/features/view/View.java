package com.example.community_engagement.features.view;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDateTime;

@Document(collection = "views")
@Data
public class View {

    @Id
    private String id;

    @NotNull(message = "userId cannot be null")
    private String contentId;

    @NotNull(message = "userId cannot be null")
    private String userId;

    private Duration viewDuration;
    private LocalDateTime createdAt;
}