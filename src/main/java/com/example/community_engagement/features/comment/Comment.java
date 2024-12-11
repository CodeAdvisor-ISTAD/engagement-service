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

    @NotNull(message = "commenting cannot be null")
    private String body;

    @NotNull(message = "createdAt cannot be null")
    @PastOrPresent(message = "createdAt must be in the past or present")
    private LocalDateTime createdAt;

    private Boolean isReported;

    private LocalDateTime updatedAt;

    private List<Reply> replies;
}