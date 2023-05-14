package com.geekster.Instagram.basic.service;

import com.geekster.Instagram.basic.model.Post;
import com.geekster.Instagram.basic.repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    IPostRepo iPostRepo;
    public Post createPost(Post post) {
       return  iPostRepo.save(post);
    }

    public Post getPostById(int id) {
        //return iPostRepo.findById(id);
        return iPostRepo.findById(id)
                .orElseThrow(() -> new  IllegalStateException("Post not found with id " + id));

    }

}
