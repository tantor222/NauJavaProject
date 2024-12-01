package com.naujava.khamitov.controller;

import com.naujava.khamitov.model.entity.Exercise;
import com.naujava.khamitov.model.entity.Question;
import com.naujava.khamitov.model.entity.User;
import com.naujava.khamitov.service.ExerciseService;
import com.naujava.khamitov.service.QuestionService;
import com.naujava.khamitov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/list")
    public String exerciseList(Model model) {
        List<Exercise> exercises = exerciseService.getAllExercises();
        model.addAttribute("exercises", exercises);
        return "exercise-list";
    }

    @GetMapping("/create")
    public String createExercise(Principal principal) {
        User user = userService.getUserByName(principal.getName()).orElseThrow(() -> new RuntimeException("Fail"));
        Exercise entity = Exercise.builder()
                .author(user.getId())
                .title("Задание " + UUID.randomUUID())
                .description("Новое задание")
                .build();
        Exercise exercise = exerciseService.createExercise(entity);
        return "redirect:/exercise/form/" + exercise.getId();
    }

    @GetMapping("/form/{exerciseId}")
    public String showCreateForm(Model model, @PathVariable String exerciseId) {
        UUID exerciseUuid = UUID.fromString(exerciseId);
        Exercise exercise = exerciseService.getById(exerciseUuid);
        List<Question> questions = questionService.getAllExerciseQuestions(exerciseUuid);
        model.addAttribute("exercise", exercise);
        model.addAttribute("questions", questions);
        return "exercise-form";
    }

    @PostMapping("/update")
    public String updateExercise(@RequestParam String id, @RequestParam String title,
                                 @RequestParam String description) {
        exerciseService.updateExercise(UUID.fromString(id), title, description);
        return "redirect:/exercise/list";
    }

    @PostMapping("/question")
    public String createQuestion(@RequestParam String exercise, @RequestParam String description,
        @RequestParam List<String> invariants, @RequestParam String answer) {
        questionService.createQuestion(Question.builder()
                .exercise(UUID.fromString(exercise))
                .description(description)
                .invariants(invariants)
                .answer(answer)
                .build());

        return "redirect:/exercise/form/" + exercise;
    }

    @GetMapping("/remove/{exerciseId}")
    public String removeExercise(@PathVariable String exerciseId) {
        exerciseService.removeExercise(UUID.fromString(exerciseId));
        return "redirect:/exercise/list" ;
    }
}