package com.example.community_engagement.features.reply;

import com.example.community_engagement.config.producer.CommentRepliedEvent;
import com.example.community_engagement.features.comment.Comment;
import com.example.community_engagement.features.comment.CommentRepository;
import com.example.community_engagement.features.reply.dto.CommentRepliedRequest;
import com.example.community_engagement.features.reply.dto.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final CommentRepliedEvent commentRepliedEvent;


    @Override
    public Reply createReply(String commentId, ReplyRequest replyRequest) {
        // Fetch the comment to ensure it exists
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Initialize the replies list if it's null
        if (comment.getReplies() == null) {
            comment.setReplies(new ArrayList<>());
        }

        // Create a new reply object
        Reply reply = new Reply();
        reply.setType("REPLIED");
        reply.setUserId(replyRequest.userId());
        reply.setCommentId(commentId);
        reply.setBody(replyRequest.body());
        reply.setCreateAt(LocalDateTime.now());
        reply.setIsReported(false);

        // Save the reply to the repository
        replyRepository.save(reply);

        // Add the reply to the comment's replies list
        comment.getReplies().add(reply);

        // Save the updated comment
        commentRepository.save(comment);

        // Produce the event to Kafka
        CommentRepliedRequest event = new CommentRepliedRequest(commentId, reply.getType(), reply.getUserId(), reply.getBody());
        commentRepliedEvent.sendCommentRepliedEvent("comment-replied-events-topic", event);

        return reply;
    }


    @Override
    public List<Reply> getRepliesByCommentId(String commentId) {
        return replyRepository.findByCommentId(commentId);
    }

    @Override
    public Reply updateReply(String replyId, ReplyRequest replyRequest) {
        // Find the existing reply to update
        Reply existingReply = replyRepository.findById(replyId)
                .orElseThrow(() -> new RuntimeException("Reply not found"));

        // Update the reply content
        existingReply.setBody(replyRequest.body());
        existingReply.setUpdatedAt(LocalDateTime.now());

        // Save the updated reply
        replyRepository.save(existingReply);

        // Find the associated comment and update the reply in the comment's replies list
        Comment comment = commentRepository.findById(existingReply.getCommentId())
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Update the reply in the list
        for (int i = 0; i < comment.getReplies().size(); i++) {
            if (comment.getReplies().get(i).getId().equals(replyId)) {
                comment.getReplies().set(i, existingReply);
                break;
            }
        }

        // Save the updated comment
        commentRepository.save(comment);

        return existingReply;
    }

    @Override
    public void deleteReply(String replyId) {
        // Find the reply to delete
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new RuntimeException("Reply not found"));

        // Find the associated comment
        Comment comment = commentRepository.findById(reply.getCommentId())
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Remove the reply from the comment's replies list
        comment.getReplies().removeIf(r -> r.getId().equals(replyId));

        // Save the updated comment
        commentRepository.save(comment);

        // Delete the reply from the repository
        replyRepository.deleteById(replyId);
    }
}
