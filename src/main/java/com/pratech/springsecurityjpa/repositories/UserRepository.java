package com.pratech.springsecurityjpa.repositories;

import com.pratech.springsecurityjpa.MyUserDetailsService;
import com.pratech.springsecurityjpa.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String username);

    @Override
    List<User> findAll();
}
