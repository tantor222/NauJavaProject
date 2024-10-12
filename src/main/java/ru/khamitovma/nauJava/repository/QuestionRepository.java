package ru.khamitovma.nauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.khamitovma.nauJava.model.entity.Question;

import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
}
