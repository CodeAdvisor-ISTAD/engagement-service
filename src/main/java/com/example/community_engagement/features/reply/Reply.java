package com.example.community_engagement.features.reply;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "replies")
@Data
public class Reply {

    @Id
    private String id;

    @NotNull(message = "userId cannot be null")
    private String userId;

    @NotNull(message = "commentId cannot be null")
    private String commentId;

    @NotNull(message = "reply cannot be null")
    private String body;

    @NotNull(message = "createdAt cannot be null")
    @PastOrPresent(message = "createdAt must be in the past or present")
    private LocalDateTime createAt;

    private LocalDateTime updatedAt;
    private Boolean isReported;
}
