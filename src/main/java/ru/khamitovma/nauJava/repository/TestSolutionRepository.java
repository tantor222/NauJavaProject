package ru.khamitovma.nauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khamitovma.nauJava.model.entity.TestSolution;

import java.util.UUID;

public interface TestSolutionRepository extends JpaRepository<TestSolution, UUID> {
}
