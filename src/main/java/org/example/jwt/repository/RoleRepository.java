package org.example.jwt.repository;

import org.example.jwt.model.Role;
import org.example.jwt.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getByStatus(Status status);

}
