package com.geekster.Instagram.basic.repository;

import com.geekster.Instagram.basic.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post,Integer> {
}
