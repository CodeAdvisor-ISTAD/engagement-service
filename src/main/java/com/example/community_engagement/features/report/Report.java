package com.example.community_engagement.features.report;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reports")
@Data
public class Report {

    @Id
    private String id;

    @NotNull(message = "dataType cannot be null")
    private String dataType;

    @NotNull(message = "userId cannot be null")
    private String userId;

    @NotNull(message = "status cannot be null")
    private String status;

    @NotNull(message = "reason cannot be null")
    private String reason;
    private String description;

    @NotNull(message = "createdAt cannot be null")
    private LocalDateTime createdAt;

    @NotNull(message = "url cannot be null")
    private String url;

    private Boolean isDeleted;
}
