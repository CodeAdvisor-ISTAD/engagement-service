package com.example.community_engagement.features.comment;

import com.example.community_engagement.features.reply.Reply;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "comments")
@Data
public class Comment {
    @Id
    private String id;

    @NotNull(message = "userId cannot be null")
    private String userId;

    @NotNull(message = "contentId cannot be null")
    private String contentId;

    @NotNull(message = "body cannot be null")
    private String body;

    @NotNull(message = "ownerId cannot be null")
    private String ownerId;

    @NotNull(message = "slug cannot be null")
    private String slug;

    @NotNull
    private String type;

    @NotNull(message = "createdAt cannot be null")
    @PastOrPresent(message = "createdAt must be in the past or present")
    private LocalDateTime createdAt;

    private Boolean isReported;

    private LocalDateTime updatedAt;

    private List<Reply> replies;
}