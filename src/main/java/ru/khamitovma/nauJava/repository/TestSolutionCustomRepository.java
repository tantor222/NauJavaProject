package ru.khamitovma.nauJava.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.khamitovma.nauJava.model.entity.TestSolution;

import java.util.List;

@RepositoryRestResource
public interface TestSolutionCustomRepository {

    List<TestSolution> findByScore(Integer score);

    List<TestSolution> findByExerciseName(String name);
}
