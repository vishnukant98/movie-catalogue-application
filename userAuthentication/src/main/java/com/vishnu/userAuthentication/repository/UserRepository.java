package com.vishnu.userAuthentication.repository;

import com.vishnu.userAuthentication.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
