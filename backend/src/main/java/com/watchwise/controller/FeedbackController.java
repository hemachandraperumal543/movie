package com.watchwise.controller;

import com.watchwise.model.Feedback;
import com.watchwise.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> feedbackList = feedbackRepository.findAllByOrderByCreatedAtDesc();
        return ResponseEntity.ok(feedbackList);
    }

    @PostMapping
    public ResponseEntity<?> submitFeedback(@RequestBody Feedback feedback) {
        if (feedback.getName() == null || feedback.getName().trim().isEmpty() ||
            feedback.getEmail() == null || feedback.getEmail().trim().isEmpty() ||
            feedback.getCategory() == null || feedback.getCategory().trim().isEmpty() ||
            feedback.getMessage() == null || feedback.getMessage().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All feedback fields are required");
        }

        feedback.setName(feedback.getName().trim());
        feedback.setEmail(feedback.getEmail().trim());
        feedback.setMessage(feedback.getMessage().trim());

        Feedback savedFeedback = feedbackRepository.save(feedback);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFeedback);
    }
}
