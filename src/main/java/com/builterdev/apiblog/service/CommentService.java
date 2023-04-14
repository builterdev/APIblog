package com.builterdev.apiblog.service;

import com.builterdev.apiblog.entity.Comment;
import com.builterdev.apiblog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long postId);

    List<CommentDto> getCommentsByPostId(Long postId);
}
