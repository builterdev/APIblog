package com.builterdev.apiblog.repository;

import com.builterdev.apiblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByPostId(Long postId);
}
