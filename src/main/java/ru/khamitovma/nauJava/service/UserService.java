package ru.khamitovma.nauJava.service;

import ru.khamitovma.nauJava.model.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User createUser();

    Optional<User> findUser(UUID uuid);
}
