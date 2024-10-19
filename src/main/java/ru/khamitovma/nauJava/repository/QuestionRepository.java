package ru.khamitovma.nauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.khamitovma.nauJava.model.entity.Question;

import java.util.UUID;

@RepositoryRestResource
public interface QuestionRepository extends JpaRepository<Question, UUID> {
}
