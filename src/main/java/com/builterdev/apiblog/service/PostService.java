package com.builterdev.apiblog.service;

import com.builterdev.apiblog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto updatePost(PostDto postDto, Long postId);

    void removePost(Long postId);
}
