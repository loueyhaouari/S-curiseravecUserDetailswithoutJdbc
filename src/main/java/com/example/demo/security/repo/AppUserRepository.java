package com.example.demo.security.repo;

import com.example.demo.security.entities.AppRole;
import com.example.demo.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
