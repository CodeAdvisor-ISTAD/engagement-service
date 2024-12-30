package com.example.community_engagement.config.producer;

import com.example.community_engagement.features.reaction.dto.ContentReactedRequest;
import com.example.community_engagement.features.reply.dto.CommentRepliedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentReactedEvent {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendContentReactedEvent(String topic, ContentReactedRequest event) {
        kafkaTemplate.send(topic, event);
    }
}
