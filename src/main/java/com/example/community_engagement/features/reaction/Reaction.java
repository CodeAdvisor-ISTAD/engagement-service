package com.example.community_engagement.features.reaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reactions")
@Data
public class Reaction {
    @Id
    private String id;

    @NotNull(message = "userId cannot be null")
    private String userId;

    @NotNull(message = "type cannot be null")
    private String type;
    
    @NotNull(message = "contentId cannot be null")
    private String contentId;

    @NotNull(message = "reaction type cannot be null")
    private String reactionType;

    @NotNull(message = "createdAt cannot be null")
    @PastOrPresent(message = "createdAt must be in the past or present")
    private LocalDateTime createdAt;

    private Boolean isDeleted;
}
