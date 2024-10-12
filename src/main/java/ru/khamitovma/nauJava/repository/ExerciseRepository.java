package ru.khamitovma.nauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khamitovma.nauJava.model.entity.Exercise;

import java.util.UUID;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
}
