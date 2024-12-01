package com.naujava.khamitov.repository;

import com.naujava.khamitov.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    List<Answer> findAllBuAuthor(UUID author);
}
