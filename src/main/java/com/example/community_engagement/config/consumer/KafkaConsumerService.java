package com.example.community_engagement.config.consumer;

import com.example.community_engagement.features.comment.dto.CommentCreatedRequest;
import com.example.community_engagement.features.reaction.dto.ContentReactedRequest;
import com.example.community_engagement.features.report.dto.CommentReportedRequest;
import com.example.community_engagement.features.report.dto.ContentReportedRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
//    @KafkaListener(topics = "comment-created-events-topic", groupId = "testing")
//    public void consumeContentReportedEvent(CommentCreatedRequest event) {
//        System.out.println("Consume Successfully!");
//        System.out.println("Consumed Content Reported Event: " + event);
//        // You can add logic to process the event here
//    }
//
//    @KafkaListener(topics = "comment-reported-events-topic",  groupId = "testing")
//    public void consumeCommentReportedEvent(CommentReportedRequest event) {
//        System.out.println("Consumed Comment Reported Event: " + event);
//        // You can add logic to process the event here
//    }
//
//    @KafkaListener(topics = "content-reacted-events-topic",  groupId = "testing")
//    public void consumeContentReactedEvent(ContentReactedRequest event) {
//        System.out.println("Consumed Content Reacted Event: " + event);
//        // You can add logic to process the event here
//    }
}
