package ru.khamitovma.nauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.khamitovma.nauJava.model.entity.TestSolution;

import java.util.UUID;

@RepositoryRestResource
public interface TestSolutionRepository extends JpaRepository<TestSolution, UUID> {
}
