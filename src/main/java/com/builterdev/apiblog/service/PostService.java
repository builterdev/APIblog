package com.builterdev.apiblog.service;

import com.builterdev.apiblog.payload.PostDto;
import com.builterdev.apiblog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize);

    PostDto updatePost(PostDto postDto, Long postId);

    void removePost(Long postId);
}
