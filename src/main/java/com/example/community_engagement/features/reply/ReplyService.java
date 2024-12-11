package com.example.community_engagement.features.reply;

import com.example.community_engagement.features.reply.dto.ReplyRequest;

import java.util.List;

public interface ReplyService {

    // Create a reply to a comment
    Reply createReply(String commentId, ReplyRequest reply);

    // Get all replies for a comment
    List<Reply> getRepliesByCommentId(String commentId);

    // Update a reply
    Reply updateReply(String replyId, ReplyRequest reply);

    // Delete a reply
    void deleteReply(String replyId);
}
