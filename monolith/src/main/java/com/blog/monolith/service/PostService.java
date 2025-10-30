package com.blog.monolith.service;

import com.blog.monolith.dto.PostDTO;
import com.blog.monolith.entity.Post;
import com.blog.monolith.entity.User;
import com.blog.monolith.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public Post createPost(PostDTO postDTO) {
        User author = userService.getUserById(postDTO.getAuthorId());

        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAuthor(author);
        post.setPublishDate(LocalDateTime.now());

        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }
}