package ru.khamitovma.nauJava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.khamitovma.nauJava.model.entity.Answer;
import ru.khamitovma.nauJava.model.entity.Exercise;
import ru.khamitovma.nauJava.model.entity.Invariant;
import ru.khamitovma.nauJava.model.entity.TestSolution;
import ru.khamitovma.nauJava.model.entity.User;
import ru.khamitovma.nauJava.repository.TestSolutionRepository;
import ru.khamitovma.nauJava.service.ExerciseService;
import ru.khamitovma.nauJava.service.TestSolutionService;
import ru.khamitovma.nauJava.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TestSolutionServiceImpl implements TestSolutionService {

    private final UserService userService;
    private final ExerciseService exerciseService;
    private final TestSolutionRepository testSolutionRepository;

    @Autowired
    public TestSolutionServiceImpl(UserService userService, ExerciseService exerciseService,
                                   TestSolutionRepository testSolutionRepository) {
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.testSolutionRepository = testSolutionRepository;
    }

    @Override
    public Optional<TestSolution> startSolution(UUID userId, UUID exerciseId) {
        Optional<User> user = userService.findUser(userId);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        Optional<Exercise> exercise = exerciseService.findExercise(exerciseId);
        if (exercise.isEmpty()) {
            return Optional.empty();
        }
        int totalScore = exercise.get().getQuestions().stream()
                .mapToInt(i -> i.getQuestion().getInvariants().stream().mapToInt(Invariant::getScore)
                        .sum())
                .sum();
        TestSolution testSolution = new TestSolution(user.get(), exercise.get(), null, 0, totalScore);
        testSolutionRepository.save(testSolution);
        testSolution.getExercise()
                .getQuestions()
                .forEach(i -> i.getQuestion().getInvariants().forEach(j -> j.setScore(0)));
        return Optional.of(testSolution);
    }

    @Override
    public TestSolution completeSolution(List<Answer> answers) {
        
        return null;
    }
}
