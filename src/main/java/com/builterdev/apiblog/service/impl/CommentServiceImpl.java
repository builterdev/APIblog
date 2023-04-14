package com.builterdev.apiblog.service.impl;

import com.builterdev.apiblog.entity.Comment;
import com.builterdev.apiblog.entity.Post;
import com.builterdev.apiblog.exception.ResourceNotFoundException;
import com.builterdev.apiblog.payload.CommentDto;
import com.builterdev.apiblog.repository.CommentRepository;
import com.builterdev.apiblog.repository.PostRepository;
import com.builterdev.apiblog.service.CommentService;
import com.builterdev.apiblog.utils.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {

        // Get post by ID
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        Comment comment = Mappers.mapToEntity(commentDto);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);

        return Mappers.mapToDTO(savedComment);

    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        // Get post by ID
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        List<Comment> comments = commentRepository.findCommentsByPostId(postId);

        return comments.stream().map(comment -> Mappers.mapToDTO(comment)).collect(Collectors.toList());
    }
}
