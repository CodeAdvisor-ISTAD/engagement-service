package com.example.community_engagement.features.share;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shares")
@Data
public class Share {
    @Id
    private String id;

    private String userId;
    private String contentId;
    private String sharePlatform;
    private String timestamp;
}
