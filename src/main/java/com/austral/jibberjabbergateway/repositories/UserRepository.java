package com.austral.jibberjabbergateway.repositories;

import com.austral.jibberjabbergateway.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,String> {
    Optional<AppUser> findByUsername(String username);
}
