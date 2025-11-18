package com.blog.commentservice.repository;

import com.blog.commentservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // We might want to find all comments for a specific post later
    List<Comment> findByPostId(Long postId);
}