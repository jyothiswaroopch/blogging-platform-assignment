package com.blog.monolith.service;

import com.blog.monolith.dto.CommentDTO;
import com.blog.monolith.entity.Comment;
import com.blog.monolith.entity.Post;
import com.blog.monolith.entity.User;
import com.blog.monolith.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    public Comment addComment(CommentDTO commentDTO) {
        Post post = postService.getPostById(commentDTO.getPostId());
        User user = userService.getUserById(commentDTO.getUserId());

        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setPost(post);
        comment.setUser(user);
        comment.setCommentDate(LocalDateTime.now());

        return commentRepository.save(comment);
    }
}