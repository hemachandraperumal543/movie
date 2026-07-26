package com.watchwise.controller;

import com.watchwise.model.MovieComment;
import com.watchwise.repository.MovieCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class MovieCommentController {

    @Autowired
    private MovieCommentRepository movieCommentRepository;

    @GetMapping("/{movieId}")
    public ResponseEntity<List<MovieComment>> getComments(@PathVariable("movieId") String movieId) {
        List<MovieComment> comments = movieCommentRepository.findByMovieIdOrderByCreatedAtDesc(movieId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<?> postComment(@RequestBody MovieComment comment) {
        if (comment.getMovieId() == null || comment.getMovieId().trim().isEmpty() ||
            comment.getUsername() == null || comment.getUsername().trim().isEmpty() ||
            comment.getText() == null || comment.getText().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movie ID, username, and comment text are required");
        }

        comment.setMovieId(comment.getMovieId().trim());
        comment.setUsername(comment.getUsername().trim());
        comment.setText(comment.getText().trim());

        MovieComment savedComment = movieCommentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }
}
