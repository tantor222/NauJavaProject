package com.naujava.khamitov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    @GetMapping
    public String mainPage(Model model) {
        return "main";
    }

    @GetMapping("/main")
    public String mainPathPage(Model model) {
        return "main";
    }
}
