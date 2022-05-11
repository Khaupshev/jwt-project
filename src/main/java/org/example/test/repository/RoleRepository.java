package org.example.test.repository;

import org.example.test.model.Role;
import org.example.test.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getByStatus(Status status);

}
