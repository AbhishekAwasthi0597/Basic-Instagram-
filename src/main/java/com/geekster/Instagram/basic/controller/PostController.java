package com.geekster.Instagram.basic.controller;

import com.geekster.Instagram.basic.model.Post;
import com.geekster.Instagram.basic.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;
    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post newPost = postService.createPost(post);
        return ResponseEntity.ok(newPost);
    }

    @GetMapping("/{id}/post")
    public ResponseEntity<Post> getPostById(@PathVariable int id){
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }
}
