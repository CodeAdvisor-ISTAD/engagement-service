package com.example.community_engagement.features.view.web;

import com.example.community_engagement.features.view.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/views")
@RequiredArgsConstructor
public class ViewController {

    private final ViewService viewService;

    // Endpoint to record a view
    @PostMapping("/record")
    public void recordView(@RequestParam String userId, @RequestParam String contentId,
                           @RequestParam String startTime, @RequestParam String endTime) {
        // Convert the startTime and endTime from String to LocalDateTime
        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);

        // Call the service to record the view
        viewService.recordView(userId, contentId, start, end);
    }

    // Endpoint to get the count of views by user and content
    @GetMapping("/count")
    public long countViews(@RequestParam String userId, @RequestParam String contentId) {
        return viewService.countUserViews(userId, contentId);
    }
}
