package com.example.community_engagement.config.producer;

import com.example.community_engagement.features.comment.dto.CommentCreatedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentCreatedEvent {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendCommentCreatedEvent(String topic, CommentCreatedRequest event) {
        kafkaTemplate.send(topic, event);
    }
}
