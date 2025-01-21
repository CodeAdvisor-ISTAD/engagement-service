package com.example.community_engagement.features.reaction.web;

import com.example.community_engagement.features.reaction.Reaction;
import com.example.community_engagement.features.reaction.ReactionService;
import com.example.community_engagement.features.reaction.dto.ContentReactedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reactions")
@RequiredArgsConstructor
public class ReactionController {
    private final ReactionService reactionService;

    // Endpoint to create a reaction for a specific content
    @PostMapping("/content/{contentId}")
    public ResponseEntity<Reaction> createReaction(
            @PathVariable("contentId") String contentId,
            @RequestBody ContentReactedRequest reactionRequest) {

        // Create the reaction using the service. If invalid, it will throw an exception
        Reaction reaction = reactionService.createReaction(contentId, reactionRequest);

        // Return the created reaction with status 201
        return ResponseEntity.status(HttpStatus.CREATED).body(reaction);
    }

    // Endpoint to delete a reaction by its ID (soft delete)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReaction(@PathVariable String id) {
        reactionService.deleteReaction(id);
    }

    // Endpoint to get reactions by contentId (with counts for each reaction type)
    @GetMapping("/content/{id}")
    public Map<String, Long> getReactionsByContentId(@PathVariable String id) {
        return reactionService.getReactionsByContentId(id);
    }

    // Endpoint to get the reaction for a specific contentId and userId
    @GetMapping("/{contentId}/user/{userId}")
    public ResponseEntity<?> getUserReaction(@PathVariable String contentId, @PathVariable String userId) {
        Optional<Reaction> reaction = reactionService.getUserReaction(contentId, userId);

        if (reaction.isPresent()) {
            return ResponseEntity.ok(reaction.get());
        } else {
            return ResponseEntity.status(404).body("No reaction found for the given user and content.");
        }
    }
}
