package com.example.community_engagement.config.producer;

import com.example.community_engagement.features.reaction.dto.ContentReactedRequest;
import com.example.community_engagement.features.report.dto.CommentReportedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentReportedEvent {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendCommentReportedEvent(String topic, CommentReportedRequest event) {
        kafkaTemplate.send(topic, event);
    }
}
