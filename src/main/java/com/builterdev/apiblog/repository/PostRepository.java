package com.builterdev.apiblog.repository;

import com.builterdev.apiblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
