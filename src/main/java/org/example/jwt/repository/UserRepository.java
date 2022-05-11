package org.example.jwt.repository;

import org.example.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Count all by user name or email int.
     *
     * @param userName
     *         the user name
     * @param email
     *         the email
     * @return the int
     */
    int countAllByUserNameOrEmail(String userName, String email);

    /**
     * Find by user name optional.
     *
     * @param userName
     *         the user name
     * @return the optional
     */
    Optional<User> findByUserName(String userName);
}
