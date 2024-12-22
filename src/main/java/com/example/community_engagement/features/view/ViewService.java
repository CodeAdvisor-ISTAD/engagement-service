package com.example.community_engagement.features.view;

import java.time.LocalDateTime;

public interface ViewService {
    // Method to record a view when the user interacts with content
    void recordView(String userId, String contentId, LocalDateTime startTime, LocalDateTime endTime);

    // Method to count views by a specific user and content
    long countUserViews(String userId, String contentId);
}
