package com.example.community_engagement.kafka;

import com.example.community_engagement.features.reaction.Reaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReactionProducer {
    private static final String REACTION_TOPIC = "engagement-reactions";  // Kafka topic for reactions
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    // Send a reaction event to Kafka
    public void sendReactionToKafka(Reaction reaction) {
        try {
            String message = objectMapper.writeValueAsString(reaction);
            kafkaTemplate.send(REACTION_TOPIC, message);
            System.out.println("Sent reaction to Kafka: " + message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
