package com.example.community_engagement.features.reaction;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface ReactionRepository extends MongoRepository<Reaction, String> {
    // Find all reactions for a specific contentId
    List<Reaction> findByContentId(String contentId);

    Optional<Reaction> findByContentIdAndUserIdAndIsDeletedFalse(String contentId, String userId);

    // Find all reactions for a specific contentId that are not deleted
    List<Reaction> findByContentIdAndIsDeletedFalse(String contentId);

    // Find a reaction by userId and contentId (to ensure one user can react only once per content)
    Reaction findByContentIdAndUserId(String contentId, String userId);

    // find if user already reacted to a content
    Boolean existsByContentIdAndUserId(String contentId, String userId);

}
