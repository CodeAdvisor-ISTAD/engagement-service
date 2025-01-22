package com.example.community_engagement.features.share.web;

import com.example.community_engagement.features.report.ReportService;
import com.example.community_engagement.features.share.Share;
import com.example.community_engagement.features.share.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shares")
@RequiredArgsConstructor
public class ShareController {
    private final ShareService shareService;

    @PostMapping("/shareContent")
    public ResponseEntity<String> shareContent(@RequestBody Share share) {
        try {
            // Call the service to create a share event
            Share createdShare = shareService.createShare(share);
            return ResponseEntity.ok("Content shared successfully!" + createdShare);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to share content.");
        }
    }

    @GetMapping("/shares/user/{userId}")
    public ResponseEntity<List<Share>> getSharesByUserId(@PathVariable String userId) {
        List<Share> shares = shareService.getSharesByUserId(userId);
        return ResponseEntity.ok(shares);
    }

    // Endpoint to get all shares by contentId
    @GetMapping("/shares/content/{contentId}")
    public ResponseEntity<List<Share>> getAllSharesByContentId(@PathVariable String contentId) {
        List<Share> shares = shareService.getAllSharesByContentId(contentId);
        return ResponseEntity.ok(shares);
    }
}
