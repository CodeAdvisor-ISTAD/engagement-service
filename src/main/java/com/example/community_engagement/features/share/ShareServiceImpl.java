package com.example.community_engagement.features.share;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShareServiceImpl implements ShareService{
    private final ShareRepository shareRepository;

    @Override
    public Share createShare(Share share) {
        // Ensure that required fields are not null
        if (share.getUserId() == null || share.getContentId() == null || share.getSharePlatform() == null) {
            throw new IllegalArgumentException("Missing required fields.");
        }

        // Set a timestamp for when the share event was created
        share.setTimestamp(java.time.LocalDateTime.now().toString());

        // Save the share event in the repository (MongoDB)
        return shareRepository.save(share);
    }

    @Override
    public List<Share> getSharesByUserId(String userId) {
        // Fetching the list of shares by userId from the database
        return shareRepository.findByUserId(userId);
    }

    // Method to get all shares by contentId
    @Override
    public List<Share> getAllSharesByContentId(String contentId) {
        // Fetching the list of shares by contentId from the database
        return shareRepository.findByContentId(contentId);
    }
}
