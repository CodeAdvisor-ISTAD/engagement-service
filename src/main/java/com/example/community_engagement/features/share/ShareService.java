package com.example.community_engagement.features.share;

import java.util.List;

public interface ShareService {
    Share createShare(Share share);
    // Method to get shares by userId
    List<Share> getSharesByUserId(String userId);

    // Method to get all shares by contentId
    List<Share> getAllSharesByContentId(String contentId);
}
