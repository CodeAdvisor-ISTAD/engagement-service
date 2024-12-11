package com.example.community_engagement.features.bookmark;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bookmarks")
@Data
public class Bookmark {
    @Id
    private String id;

    @NotNull(message = "userId cannot be null")
    private String userId;

    @NotNull(message = "contentId cannot be null")
    private String contentId;
    private Boolean isBookmarked;

    @NotNull(message = "createdAt cannot be null")
    @PastOrPresent(message = "createdAt must be in the past or present")
    private LocalDateTime createdAt;
}
