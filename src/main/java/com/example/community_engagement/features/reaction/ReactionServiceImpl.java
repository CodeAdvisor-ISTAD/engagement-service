package com.example.community_engagement.features.reaction;

import com.example.community_engagement.features.reaction.dto.ReactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;

    // Create a reaction for a specific content
    @Override
    public Reaction createReaction(String contentId, ReactionRequest reactionRequest) {
        // Ensure that contentId in the request body matches the contentId in the path
        if (!contentId.equals(reactionRequest.contentId())) {
            throw new IllegalArgumentException("Content ID in the request body does not match the URL");
        }

        // Validate the reaction type
        if (!isValidReactionType(reactionRequest.reactionType())) {
            throw new IllegalArgumentException("Invalid reaction type");
        }

        // Ensure the contentId is valid (this can be extended to check if the content exists)
        if (reactionRequest.contentId() == null || reactionRequest.contentId().isEmpty()) {
            throw new IllegalArgumentException("Content ID is required");
        }

        // Create the new reaction
        Reaction reaction = new Reaction();
        reaction.setContentId(reactionRequest.contentId());
        reaction.setUserId(reactionRequest.userId());
        reaction.setReactionType(reactionRequest.reactionType());
        reaction.setCreatedAt(LocalDateTime.now());
        reaction.setIsDeleted(false);

        // Save and return the created reaction
        return reactionRepository.save(reaction);
    }

    @Override
    public void deleteReaction(String reactionId) {
        // Find the reaction by ID
        Reaction reaction = reactionRepository.findById(reactionId)
                .orElseThrow(() -> new RuntimeException("Reaction not found"));

        // Mark as deleted (soft delete)
        reaction.setIsDeleted(true);

        // Save the updated reaction with isDeleted set to true
        reactionRepository.save(reaction);
    }

    @Override
    public Map<String, Long> getReactionsByContentId(String contentId) {
        // Fetch all reactions for the given contentId
        List<Reaction> reactions = reactionRepository.findByContentId(contentId);

        // Initialize default counts for each reaction type
        Map<String, Long> reactionCounts = new HashMap<>();
        reactionCounts.put("love", 0L);
        reactionCounts.put("like", 0L);
        reactionCounts.put("fire", 0L);

        // Count reactions by type, excluding deleted ones
        reactions.stream()
                .filter(reaction -> !reaction.getIsDeleted())  // Exclude deleted reactions
                .forEach(reaction -> {
                    // Update the count for the specific reaction type
                    reactionCounts.put(reaction.getReactionType(), reactionCounts.get(reaction.getReactionType()) + 1);
                });

        return reactionCounts;
    }

    // Validate the reaction type (either 'love', 'like', or 'fire')
    private boolean isValidReactionType(String reactionType) {
        return reactionType.equalsIgnoreCase("love") ||
                reactionType.equalsIgnoreCase("like") ||
                reactionType.equalsIgnoreCase("fire");
    }
}