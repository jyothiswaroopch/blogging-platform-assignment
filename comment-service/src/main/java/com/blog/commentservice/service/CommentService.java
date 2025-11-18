package com.blog.commentservice.service;

import com.blog.commentservice.dto.CommentDTO;
import com.blog.commentservice.entity.Comment;
import com.blog.commentservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate; // Import this!
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestTemplate restTemplate; // Inject RestTemplate

    public Comment addComment(CommentDTO commentDTO) {
        // 1. Validate User exists (Call User Service)
        try {
            restTemplate.getForObject("http://user-service:8081/users/" + commentDTO.getUserId(), Object.class);
        } catch (Exception e) {
            throw new RuntimeException("User not found");
        }

        // 2. Validate Post exists (Call Post Service)
        try {
            restTemplate.getForObject("http://post-service:8082/posts/" + commentDTO.getPostId(), Object.class);
        } catch (Exception e) {
            throw new RuntimeException("Post not found");
        }

        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setPostId(commentDTO.getPostId());
        comment.setUserId(commentDTO.getUserId());
        comment.setCommentDate(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}