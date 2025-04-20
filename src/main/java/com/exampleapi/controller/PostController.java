package com.exampleapi.controller;

import com.exampleapi.repository.CommentRepository;
import com.exampleapi.entity.Post;
import com.exampleapi.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private PostRepository postRepository;
    private CommentRepository commentRep;

    public PostController(PostRepository postRepository, CommentRepository commentRep) {
        this.postRepository = postRepository;
        this.commentRep = commentRep;
    }

    @PostMapping
    public String createPost(
            @RequestBody Post post
            ){
        postRepository.save(post);
         return null;
    }

    @DeleteMapping
    public void deletePost(){
        postRepository.deleteById(1L);
    }
}
