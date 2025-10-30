package com.blog.monolith.controller;

import com.blog.monolith.dto.CommentDTO;
import com.blog.monolith.entity.Comment;
import com.blog.monolith.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody CommentDTO commentDTO) {
        Comment newComment = commentService.addComment(commentDTO);
        return ResponseEntity.ok(newComment);
    }
}