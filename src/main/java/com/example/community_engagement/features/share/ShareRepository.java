package com.example.community_engagement.features.share;

import com.example.community_engagement.features.share.Share;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShareRepository extends MongoRepository<Share, String> {
    // Method to find shares by userId
    List<Share> findByUserId(String userId);

    // Method to find shares by contentId
    List<Share> findByContentId(String contentId);
}
