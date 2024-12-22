package com.example.community_engagement.features.reply;

import com.example.community_engagement.features.comment.Comment;
import com.example.community_engagement.features.comment.CommentRepository;
import com.example.community_engagement.features.comment.CommentService;
import com.example.community_engagement.features.reply.dto.ReplyRequest;
import com.example.community_engagement.kafka.ReplyProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;
    private final ReplyProducer replyProducer;

    // Create a reply to a comment
//    @Override
//    public Reply createReply(String commentId, ReplyRequest replyRequest) {
//        // Fetch the comment to make sure it exists
//        Comment comment = commentRepository.findById(commentId)
//                .orElseThrow(() -> new RuntimeException("Comment not found"));
//
//        // Initialize the replies list if it is null
//        if (comment.getReplies() == null) {
//            comment.setReplies(new ArrayList<>());  // Initialize the list if it's null
//        }
//
//        // Create and save the reply
//        Reply reply = new Reply();
//        reply.setUserId(replyRequest.userId());
//        reply.setCommentId(commentId);
//        reply.setBody(replyRequest.body());
//        reply.setCreateAt(LocalDateTime.now());
//        reply.setIsReported(false);
//
//        replyRepository.save(reply);
//
//        // Add the reply to the comment's replies list
//        comment.getReplies().add(reply);
//
//        // Save the updated comment
//        commentRepository.save(comment);
//        return reply;
//    }

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

        // Send the reply to Kafka after it is saved
        replyProducer.sendReply(reply);

        return reply;
    }

    @Override
    public List<Reply> getRepliesByCommentId(String commentId) {
        return replyRepository.findByCommentId(commentId);
    }

//    @Override
//    public Reply updateReply(String replyId, ReplyRequest replyRequest) {
//        // Find the reply to update
//        Reply existingReply = replyRepository.findById(replyId)
//                .orElseThrow(() -> new RuntimeException("Reply not found"));
//
//        // Update the reply content
//        existingReply.setBody(replyRequest.body());
//        existingReply.setUpdatedAt(LocalDateTime.now());
//
//        // Save the updated reply in the reply repository
//        replyRepository.save(existingReply);
//
//        // Now find the associated comment using the reply's commentId
//        Comment comment = commentRepository.findById(existingReply.getCommentId())
//                .orElseThrow(() -> new RuntimeException("Comment not found"));
//
//        // Find the reply in the comment's replies list and update it
//        for (int i = 0; i < comment.getReplies().size(); i++) {
//            if (comment.getReplies().get(i).getId().equals(replyId)) {
//                comment.getReplies().set(i, existingReply);  // Update the reply in the list
//                break;
//            }
//        }
//
//        // Save the updated comment with the modified replies list
//        commentRepository.save(comment);
//
//        return existingReply;
//    }

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

        // Send the updated reply to Kafka
        replyProducer.sendReply(existingReply);

        return existingReply;
    }


//    @Override
//    public void deleteReply(String replyId) {
//        // Find the reply to delete
//        Reply reply = replyRepository.findById(replyId)
//                .orElseThrow(() -> new RuntimeException("Reply not found"));
//
//        // Find the associated comment
//        Comment comment = commentRepository.findById(reply.getCommentId())
//                .orElseThrow(() -> new RuntimeException("Comment not found"));
//
//        // Remove the reply from the comment's replies list
//        comment.getReplies().removeIf(r -> r.getId().equals(replyId));
//
//        // Save the updated comment
//        commentRepository.save(comment);
//
//        // Delete the reply from the repository
//        replyRepository.deleteById(replyId);
//    }

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

        // Send the deleted reply event to Kafka (for record-keeping or auditing purposes)
        replyProducer.sendReply(reply);
    }
}
