package org.example.jwt.repository;

import org.example.jwt.model.Role;
import org.example.jwt.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Role repository.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Gets by status.
     *
     * @param status
     *         the status
     * @return the by status
     */
    Role getByStatus(Status status);

}
