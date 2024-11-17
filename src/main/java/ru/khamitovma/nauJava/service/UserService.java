package ru.khamitovma.nauJava.service;

import ru.khamitovma.nauJava.model.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User createUser(User user);

    Optional<User> findUser(UUID uuid);

    Long getUsersCount();
}
