package com.vishnu.userAuthentication.repository;

import com.vishnu.userAuthentication.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
