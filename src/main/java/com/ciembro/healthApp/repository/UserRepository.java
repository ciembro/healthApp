package com.ciembro.healthApp.repository;

import com.ciembro.healthApp.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

   Optional<User> findByUsername(String username);
}
