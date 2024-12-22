package com.example.community_engagement.features.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService{

    private final ViewRepository viewRepository;

    // Implementing the recordView method
    @Override
    public void recordView(String userId, String contentId, LocalDateTime startTime, LocalDateTime endTime) {
        // Calculate the duration between the start and end times
        Duration duration = Duration.between(startTime, endTime);

        // Only count views if duration is between 5 and 10 seconds
        if (duration.getSeconds() >= 5 && duration.getSeconds() <= 10) {
            // Create a new view object
            View view = new View();
            view.setUserId(userId);
            view.setContentId(contentId);
            view.setViewDuration(duration);  // This stores the duration of the view
            view.setCreatedAt(LocalDateTime.now()); // Timestamp when the view was created

            // Save the view to the database
            viewRepository.save(view);
        }
    }

    // Implementing the countUserViews method
    @Override
    public long countUserViews(String userId, String contentId) {
        // Count how many views exist for the specified user and content
        return viewRepository.countByUserIdAndContentId(userId, contentId);
    }
}
