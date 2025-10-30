package com.blog.monolith.controller;

import com.blog.monolith.dto.PostDTO;
import com.blog.monolith.entity.Post;
import com.blog.monolith.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDTO postDTO) {
        Post createdPost = postService.createPost(postDTO);
        return ResponseEntity.ok(createdPost);
    }
}