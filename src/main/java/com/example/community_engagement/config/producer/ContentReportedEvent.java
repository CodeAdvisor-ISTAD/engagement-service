package com.example.community_engagement.config.producer;

import com.example.community_engagement.features.reaction.dto.ContentReactedRequest;
import com.example.community_engagement.features.report.dto.ContentReportedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentReportedEvent {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendContentReportedEvent(String topic, ContentReportedRequest event) {
        kafkaTemplate.send(topic, event);
    }
}
