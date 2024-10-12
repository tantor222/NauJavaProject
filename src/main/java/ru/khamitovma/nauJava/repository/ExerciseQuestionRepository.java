package ru.khamitovma.nauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.khamitovma.nauJava.model.entity.ExerciseQuestion;

public interface ExerciseQuestionRepository extends CrudRepository<ExerciseQuestion, Long> {
}
