package com.example.community_engagement.config.producer;

import com.example.community_engagement.features.comment.dto.CommentCreatedRequest;
import com.example.community_engagement.features.reply.dto.CommentRepliedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentRepliedEvent {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendCommentRepliedEvent(String topic, CommentRepliedRequest event) {
        kafkaTemplate.send(topic, event);
    }
}
