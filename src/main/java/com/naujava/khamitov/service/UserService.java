package com.naujava.khamitov.service;

import com.naujava.khamitov.model.constant.Role;
import com.naujava.khamitov.model.entity.User;
import com.naujava.khamitov.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Optional<User> getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByName(username)
                .map(user -> org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(Optional.ofNullable(user.getRole()).map(Role::name).orElse(Role.GUEST.name()))
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    public User updateUser(User user) {
        User entity = getUserById(user.getId()).orElseThrow(() -> new ResourceNotFoundException(""));
        entity.setRole(user.getRole());
        userRepository.save(entity);
        return entity;
    }

    public void removeUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
