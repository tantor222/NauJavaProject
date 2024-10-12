package ru.khamitovma.nauJava.service;

import ru.khamitovma.nauJava.model.entity.Invariant;
import ru.khamitovma.nauJava.model.entity.Question;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionService {

    Question createQuestion(Question question);

    Optional<Question> findQuestion(UUID uuid);

    Question addInvariant(UUID uuid, Invariant invariant);

    List<Question> getAllQuestions();
}
