package com.blog.postservice.service;

import com.blog.postservice.dto.PostDTO;
import com.blog.postservice.entity.Post;
import com.blog.postservice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate; // Import this!
import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RestTemplate restTemplate; // Inject RestTemplate

    public Post createPost(PostDTO postDTO) {
        // 1. Call User Service to check if user exists
        // Note: We use the container name 'user-service' which will work inside Docker
        try {
            restTemplate.getForObject("http://user-service:8081/users/" + postDTO.getAuthorId(), Object.class);
        } catch (Exception e) {
            throw new RuntimeException("User not found with id: " + postDTO.getAuthorId());
        }

        // 2. If no error, proceed to save
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAuthorId(postDTO.getAuthorId());
        post.setPublishDate(LocalDateTime.now());

        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }
}