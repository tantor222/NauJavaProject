package com.naujava.khamitov.controller;

import com.naujava.khamitov.model.constant.Role;
import com.naujava.khamitov.model.dto.AnswerDto;
import com.naujava.khamitov.model.entity.Answer;
import com.naujava.khamitov.model.entity.User;
import com.naujava.khamitov.service.AnswerService;
import com.naujava.khamitov.service.QuestionService;
import com.naujava.khamitov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/list")
    public String answerList(Principal principal, Model model) {
        User user = userService.getUserByName(principal.getName()).orElseThrow(() -> new RuntimeException(""));
        if (Role.ADMIN.equals(user.getRole())) {
            model.addAttribute("answers", answerService.getAllAnswers());
        } else {
            model.addAttribute("answers", answerService.getAllUserAnswers(user.getId()));
        }
        return "answer-list";
    }

    @GetMapping("/create/{exerciseId}")
    public String createAnswer(Principal principal, Model model, @PathVariable String exerciseId) {
        User user = userService.getUserByName(principal.getName()).orElseThrow(() -> new RuntimeException("Fail"));
        Answer answer = answerService.createAnswer(UUID.fromString(exerciseId), user.getId());
        return "redirect:/answer/form/" + answer.getId();
    }

    @GetMapping("/form/{answerId}")
    public String showAnswerForm(Model model, @PathVariable String answerId) {
        Answer answer = answerService.getAnswerById(UUID.fromString(answerId))
                .orElseThrow(() -> new RuntimeException("123"));
        AnswerDto answerDto = AnswerDto.builder()
                .id(answer.getId())
                .title("Ответ на задание: " + answer.getExercise())
                .score(answer.getScore())
                .questions(questionService.getAllAnswerQuestions(answer))
                .isCompleted(answer.isCompleted())
                .build();

        model.addAttribute("answer", answerDto);
        return "answer-form";
    }

    @PostMapping("/submit/{answerId}")
    @Transactional
    public String submitAnswerForm(@ModelAttribute AnswerDto answerDto) {
        answerService.submitAnswer(answerDto);
        return "redirect:/answer/list";
    }
}
