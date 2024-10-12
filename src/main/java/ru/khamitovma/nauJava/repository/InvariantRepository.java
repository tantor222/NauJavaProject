package ru.khamitovma.nauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.khamitovma.nauJava.model.entity.Invariant;

import java.util.List;
import java.util.UUID;

public interface InvariantRepository extends JpaRepository<Invariant, UUID> {

    List<Invariant> findByScoreBetween(int start, int end);

    @Query("select inv from Invariant inv where inv.question.id = :questionId")
    List<Invariant> findAllQuestionInvariants(UUID questionId);


}
