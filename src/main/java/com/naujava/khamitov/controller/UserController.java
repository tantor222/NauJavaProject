package com.naujava.khamitov.controller;

import com.naujava.khamitov.model.constant.Role;
import com.naujava.khamitov.model.entity.User;
import com.naujava.khamitov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public String exerciseList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/form/{userId}")
    public String showCreateForm(Model model, @PathVariable String userId) {
        UUID userUuid = UUID.fromString(userId);
        User user = userService.getUserById(userUuid).orElseThrow(() -> new RuntimeException("Not found"));
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-form";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/remove/{userId}")
    public String removeUser(Principal principal, @PathVariable String userId) {
        UUID userUuid = UUID.fromString(userId);
        User user = userService.getUserById(userUuid).orElseThrow(() -> new ResourceNotFoundException(""));
        if (!user.getUsername().equals(principal.getName())) {
            userService.removeUser(userUuid);
        }
        return "redirect:/user/list";
    }
}
