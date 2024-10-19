package ru.khamitovma.nauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.khamitovma.nauJava.model.entity.Exercise;

import java.util.UUID;

@RepositoryRestResource
public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
}
