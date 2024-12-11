package com.example.community_engagement.features.reaction;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ReactionRepository extends MongoRepository<Reaction, String> {
    // Find all reactions for a specific contentId
    List<Reaction> findByContentId(String contentId);

    // Find all reactions for a specific contentId that are not deleted
    List<Reaction> findByContentIdAndIsDeletedFalse(String contentId);

    // Find a reaction by userId and contentId (to ensure one user can react only once per content)
    Reaction findByContentIdAndUserId(String contentId, String userId);

}
