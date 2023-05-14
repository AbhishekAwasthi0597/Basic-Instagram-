package com.geekster.Instagram.basic.repository;

import com.geekster.Instagram.basic.model.AuthenticationToken;
import com.geekster.Instagram.basic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenRepo extends JpaRepository<AuthenticationToken, Long>{
    AuthenticationToken findByUser(User user);

    AuthenticationToken findFirstByToken(String token);
}
