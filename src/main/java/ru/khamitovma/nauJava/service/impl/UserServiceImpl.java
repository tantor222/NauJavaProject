package ru.khamitovma.nauJava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khamitovma.nauJava.model.entity.User;
import ru.khamitovma.nauJava.repository.UserRepository;
import ru.khamitovma.nauJava.service.UserService;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser() {
        return userRepository.save(new User());
    }

    @Override
    public Optional<User> findUser(UUID uuid) {
        return userRepository.findById(uuid);
    }
}
