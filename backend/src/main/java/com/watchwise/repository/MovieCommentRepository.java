package com.watchwise.repository;

import com.watchwise.model.MovieComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovieCommentRepository extends JpaRepository<MovieComment, Long> {
    List<MovieComment> findByMovieIdOrderByCreatedAtDesc(String movieId);
}
