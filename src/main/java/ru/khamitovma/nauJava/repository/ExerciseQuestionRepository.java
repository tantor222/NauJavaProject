package ru.khamitovma.nauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.khamitovma.nauJava.model.entity.ExerciseQuestion;

@RepositoryRestResource
public interface ExerciseQuestionRepository extends CrudRepository<ExerciseQuestion, Long> {
}
