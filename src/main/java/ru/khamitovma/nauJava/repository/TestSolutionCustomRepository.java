package ru.khamitovma.nauJava.repository;

import ru.khamitovma.nauJava.model.entity.TestSolution;

import java.util.List;

public interface TestSolutionCustomRepository {

    List<TestSolution> findByScore(Integer score);

    List<TestSolution> findByExerciseName(String name);
}
