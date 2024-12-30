package com.example.community_engagement.features.reaction;

import com.example.community_engagement.features.reaction.dto.ContentReactedRequest;

import java.util.Map;

public interface ReactionService {

    // Create a reaction for a specific content
    Reaction createReaction(String contentId, ContentReactedRequest reactionRequest);

    // Get all reactions for a specific content
    Map<String, Long> getReactionsByContentId(String contentId);

    // Delete a user's reaction for a specific content
    void deleteReaction(String reactionId);
}
