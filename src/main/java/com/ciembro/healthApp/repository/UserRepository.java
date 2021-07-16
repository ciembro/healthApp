package com.ciembro.healthApp.repository;

import com.ciembro.healthApp.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

   Optional<User> findByUsername(String username);

   Optional<User> findById(long id);
}
