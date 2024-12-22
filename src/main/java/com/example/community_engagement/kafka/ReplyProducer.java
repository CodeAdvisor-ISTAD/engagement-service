package com.example.community_engagement.kafka;

import com.example.community_engagement.features.reply.Reply;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyProducer {
    private static final String REPLY_TOPIC = "engagement-content-replies";  // Define your topic name
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    // Method to send the reply to Kafka
    public void sendReply(Reply reply) {
        try {
            String message = objectMapper.writeValueAsString(reply);
            kafkaTemplate.send(REPLY_TOPIC, message);
            System.out.println("Sent reply to Kafka: " + message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
