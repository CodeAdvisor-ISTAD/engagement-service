package com.example.community_engagement.features.reply;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReplyRepository extends MongoRepository<Reply, String> {
    // Find all replies for a specific commentId
    List<Reply> findByCommentId(String commentId);
}
