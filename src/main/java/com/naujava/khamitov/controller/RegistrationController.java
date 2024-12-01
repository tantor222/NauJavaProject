package com.naujava.khamitov.controller;

import com.naujava.khamitov.model.constant.Role;
import com.naujava.khamitov.model.entity.User;
import com.naujava.khamitov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String adduser(User user, Model model) {
        try {
            user.setRole(Role.GUEST);
            userService.createUser(user);
            return "redirect:/login";
        } catch (Exception ex) {
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }
}
