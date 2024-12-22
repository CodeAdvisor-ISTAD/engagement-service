package com.example.community_engagement.features.view;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ViewRepository extends MongoRepository<View, String> {
    // Count views by userId and contentId
    long countByUserIdAndContentId(String userId, String contentId);
}
