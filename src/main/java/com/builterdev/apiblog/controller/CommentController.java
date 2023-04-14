package com.builterdev.apiblog.controller;

import com.builterdev.apiblog.payload.CommentDto;
import com.builterdev.apiblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable(name = "postId") Long postId){
        CommentDto createdComment = commentService.createComment(commentDto, postId);

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable(name = "postId") Long postId){
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId), HttpStatus.OK);
    }

}
