package com.blog.commentservice.dto;

public class CommentDTO {
    private String text;
    private Long postId;
    private Long userId;

    // Getters and Setters
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}