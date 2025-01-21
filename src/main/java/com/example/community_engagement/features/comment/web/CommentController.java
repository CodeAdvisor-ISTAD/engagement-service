package com.example.community_engagement.features.comment.web;

import com.example.community_engagement.features.comment.Comment;
import com.example.community_engagement.features.comment.CommentService;
import com.example.community_engagement.features.comment.dto.CommentCreatedRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://192.168.56.1:3000")
@RequestMapping("/api/v1/engagement/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // Create a new comment
    @PostMapping
    public ResponseEntity<Comment> createComment(
            @Valid @RequestBody CommentCreatedRequest commentCreatedRequest) {
        Comment createdComment = commentService.createComment(commentCreatedRequest);
        return ResponseEntity.ok(createdComment);
    }

    // Get a comment by id
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a comment
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable String id, @Valid @RequestBody CommentCreatedRequest createCommentRequest) {
        try {
            Comment updatedComment = commentService.updateComment(id, createCommentRequest);
            return ResponseEntity.ok(updatedComment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a comment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    // Get all comments
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    // Get all comments by content id
    @GetMapping("/content/{contentId}")
    public ResponseEntity<?> getCommentsByContentId(@PathVariable String contentId) {
        try {
            // Call service to get the comments by contentId
            List<Comment> comments = commentService.getCommentsByContentId(contentId);
            // Check if comments list is empty and return appropriate message
            if (comments.isEmpty()) {
                return ResponseEntity.status(404).body("contentId " + contentId + " has no comment");
            }
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            // Handle any unexpected errors and return a generic error message
            return ResponseEntity.status(500).body("An error occurred while retrieving comments: " + e.getMessage());
        }
    }
}
