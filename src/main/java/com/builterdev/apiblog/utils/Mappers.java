package com.builterdev.apiblog.utils;

import com.builterdev.apiblog.entity.Comment;
import com.builterdev.apiblog.entity.Post;
import com.builterdev.apiblog.payload.CommentDto;
import com.builterdev.apiblog.payload.PostDto;

import java.util.stream.Collectors;

public class Mappers {

    public static Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    public static PostDto mapToDTO(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        postDto.setComments(
                post.getComments().stream().map(comment -> mapToDTO(comment)).collect(Collectors.toSet())
        );

        return postDto;
    }

    public static Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        return comment;
    }

    public static CommentDto mapToDTO(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;
    }

}
