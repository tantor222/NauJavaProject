package ru.khamitovma.nauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khamitovma.nauJava.model.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
