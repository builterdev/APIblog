package com.builterdev.apiblog.service.impl;

import com.builterdev.apiblog.entity.Post;
import com.builterdev.apiblog.exception.ResourceNotFoundException;
import com.builterdev.apiblog.payload.PostDto;
import com.builterdev.apiblog.repository.PostRepository;
import com.builterdev.apiblog.service.PostService;
import com.builterdev.apiblog.utils.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert data to entity
        Post post = Mappers.mapToEntity(postDto);

        Post savedPost = postRepository.save(post);

        return Mappers.mapToDTO(savedPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> Mappers.mapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post saved = postRepository.save(post);

        return Mappers.mapToDTO(saved);
    }

    @Override
    public void removePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        postRepository.delete(post);
    }
}
