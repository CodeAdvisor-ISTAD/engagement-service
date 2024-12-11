package com.example.community_engagement.features.reaction;

import com.example.community_engagement.features.reaction.dto.ReactionRequest;

import java.util.List;
import java.util.Map;

public interface ReactionService {

    // Create a reaction for a specific content
    Reaction createReaction(String contentId, ReactionRequest reactionRequest);

    // Get all reactions for a specific content
    Map<String, Long> getReactionsByContentId(String contentId);

    // Delete a user's reaction for a specific content
    void deleteReaction(String reactionId);
}
