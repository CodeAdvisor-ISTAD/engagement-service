package com.example.community_engagement.kafka;

import com.example.community_engagement.features.comment.Comment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentProducer {
    private static final String COMMENT_TOPIC = "engagement-content-comments";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    // Method to send the comment to Kafka
    public void sendComment(Comment comment) {
        try {
            String message = objectMapper.writeValueAsString(comment);
            kafkaTemplate.send(COMMENT_TOPIC, message);
            System.out.println("Sent comment to Kafka: " + message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
