package com.geekster.Instagram.basic.repository;

import com.geekster.Instagram.basic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {
    User findFirstByEmail(String email);
}
