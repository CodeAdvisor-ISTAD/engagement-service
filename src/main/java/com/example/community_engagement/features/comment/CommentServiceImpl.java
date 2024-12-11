package com.example.community_engagement.features.comment;

import com.example.community_engagement.features.comment.dto.CreateCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Comment createComment(CreateCommentRequest createCommentRequest) {
        // Create a new comment object from the request
        Comment comment = new Comment();
        comment.setUserId(createCommentRequest.userId());
        comment.setContentId(createCommentRequest.contentId());
        comment.setBody(createCommentRequest.body());
        comment.setCreatedAt(LocalDateTime.now()); // Set the current time for creation
        comment.setIsReported(false); // Optional: default value
        comment.setUpdatedAt(null); // No updates yet
        comment.setReplies(null); // No replies initially

        // Save the comment to the database
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> getCommentById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment updateComment(String id, CreateCommentRequest createCommentRequest) {
        Optional<Comment> existingComment = commentRepository.findById(id);
        if (existingComment.isPresent()) {
            // Update the existing comment with new data
            Comment comment = existingComment.get();
            comment.setUserId(createCommentRequest.userId());
            comment.setContentId(createCommentRequest.contentId());
            comment.setBody(createCommentRequest.body());
            comment.setUpdatedAt(LocalDateTime.now()); // Set the update time
            // Optional: update other fields as needed (e.g., isReported)

            // Save the updated comment to the database
            return commentRepository.save(comment);
        } else {
            throw new RuntimeException("Comment not found with id: " + id);
        }
    }

    @Override
    public void deleteComment(String id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsByContentId(String contentId) {
        return commentRepository.findByContentId(contentId);
    }

    public Boolean existsById(String commentId) {
        return commentRepository.existsById(commentId);
    }
}

