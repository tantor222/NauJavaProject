package com.naujava.khamitov.repository;

import com.naujava.khamitov.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.UUID;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
