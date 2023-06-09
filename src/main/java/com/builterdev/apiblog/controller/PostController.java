package com.builterdev.apiblog.controller;

import com.builterdev.apiblog.payload.PostDto;
import com.builterdev.apiblog.payload.PostResponse;
import com.builterdev.apiblog.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getPosts(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize
    ){
        return new ResponseEntity<>(postService.getAllPosts(pageNo, pageSize), HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable(name = "postId") Long postId) {
        return new ResponseEntity<>(postService.updatePost(postDto, postId), HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "postId") Long postId){
        postService.removePost(postId);
        return new ResponseEntity<>("Deleted with success", HttpStatus.OK);
    }

}
