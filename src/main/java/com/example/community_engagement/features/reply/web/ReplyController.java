package com.example.community_engagement.features.reply.web;

import com.example.community_engagement.features.reply.Reply;
import com.example.community_engagement.features.reply.ReplyService;
import com.example.community_engagement.features.reply.dto.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/engagement/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    // Create a reply to a comment
    @PostMapping("{id}")
    public ResponseEntity<Reply> createReply(@PathVariable String id, @RequestBody ReplyRequest reply) {
        Reply createdReply = replyService.createReply(id, reply);
        return ResponseEntity.status(201).body(createdReply);
    }

    // Get all replies for a specific commentId
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<?> getRepliesByCommentId(@PathVariable String commentId) {
        List<Reply> replies = replyService.getRepliesByCommentId(commentId);

        if (replies.isEmpty()) {
            // Return a custom message if no replies are found
            return ResponseEntity.status(404).body("No replies found for commentId " + commentId);
        }
        return ResponseEntity.ok(replies);
    }

    // Update an existing reply
    @PutMapping("/{id}")
    public ResponseEntity<Reply> updateReply(
            @PathVariable String id,            // Path variable for replyId
            @RequestBody ReplyRequest replyRequest   // Request body for the updated reply
    ) {
        try {
            // Call the service method to update the reply
            Reply updatedReply = replyService.updateReply(id, replyRequest);
            return new ResponseEntity<>(updatedReply, HttpStatus.OK); // Return the updated reply with a 200 status
        } catch (RuntimeException e) {
            // If the reply or comment is not found, return an error response
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Return 404 status if not found
        }
    }

    // Delete a reply
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReply(@PathVariable String id) {
        try {
            // Call the service method to delete the reply
            replyService.deleteReply(id);
            return new ResponseEntity<>("Reply deleted successfully", HttpStatus.NO_CONTENT); // 204 No Content
        } catch (RuntimeException e) {
            // If the reply is not found, return an error response
            return new ResponseEntity<>("Reply not found", HttpStatus.NOT_FOUND); // Return 404 status
        }
    }
}
