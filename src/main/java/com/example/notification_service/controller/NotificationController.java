package com.example.notification_service.controller;

import com.example.notification_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendNotification")
    public ResponseEntity<?> send(@RequestBody Map<String, String> payload) {
        try {
            emailService.sendDynamicEmail(
                payload.get("tomail"), 
                payload.get("subject"), 
                payload.get("body")
            );
            return ResponseEntity.ok(Map.of("status", "SUCCESS", "message", "Email sent"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("status", "FAILED", "error", "Unable to send email"));
        }
    }
}