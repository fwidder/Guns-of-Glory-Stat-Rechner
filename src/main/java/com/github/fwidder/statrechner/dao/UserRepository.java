package com.github.fwidder.statrechner.dao;

import com.github.fwidder.statrechner.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
