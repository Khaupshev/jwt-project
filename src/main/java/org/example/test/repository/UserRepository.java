package org.example.test.repository;

import org.example.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    int countAllByUserNameOrEmail(String userName, String email);

    Optional<User> findByUserName(String userName);
}
