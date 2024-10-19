package ru.khamitovma.nauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.khamitovma.nauJava.model.entity.User;
import ru.khamitovma.nauJava.repository.UserRepository;

@Controller
@RequestMapping("/custom/users/view")
public class UserControllerView
{
    private final UserRepository userRepository;

    @Autowired
    public UserControllerView(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/list")
    public String userListView(Model model)
    {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/err")
    public String getErrorMessage(Model model)
    {
        throw new RuntimeException("Test exceprion");
    }
}