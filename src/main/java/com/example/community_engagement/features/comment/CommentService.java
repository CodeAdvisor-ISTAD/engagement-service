package com.example.community_engagement.features.comment;

import com.example.community_engagement.features.comment.dto.CreateCommentRequest;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment createComment(CreateCommentRequest comment);
    Optional<Comment> getCommentById(String id);
    Comment updateComment(String id, CreateCommentRequest comment);
    void deleteComment(String id);
    List<Comment> getAllComments();
    List<Comment> getCommentsByContentId(String contentId);
    Boolean existsById(String commentId);

}
