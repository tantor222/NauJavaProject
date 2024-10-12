package ru.khamitovma.nauJava.service;

import ru.khamitovma.nauJava.model.entity.Answer;
import ru.khamitovma.nauJava.model.entity.TestSolution;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TestSolutionService {

    Optional<TestSolution> startSolution(UUID userId, UUID exerciseId);

    TestSolution completeSolution(List<Answer> answers);
}
