package com.naujava.khamitov.repository;

import com.naujava.khamitov.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface QuestionRepository extends JpaRepository<Question, UUID> {

    List<Question> findAllByExercise(UUID exercise);
}
