package com.example.community_engagement.features.comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    // Find all comments for a specific contentId
    List<Comment> findByContentId(String contentId);

    Comment save(Comment comment);
}
