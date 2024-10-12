package ru.khamitovma.nauJava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khamitovma.nauJava.model.entity.Invariant;
import ru.khamitovma.nauJava.model.entity.Question;
import ru.khamitovma.nauJava.repository.QuestionRepository;
import ru.khamitovma.nauJava.service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question createQuestion(Question question) {
        Question entity = new Question(
                question.getDescription(),
                new ArrayList<>()
        );

        for (Invariant invariant : question.getInvariants()) {
            entity.getInvariants().add(new Invariant(entity, invariant.getDescription(), invariant.getScore()));
        }
        return questionRepository.save(entity);
    }

    @Override
    public Optional<Question> findQuestion(UUID uuid) {
        return questionRepository.findById(uuid);
    }

    @Override
    public Question addInvariant(UUID uuid, Invariant invariant) {
        Optional<Question> question = questionRepository.findById(uuid);
        if (question.isPresent()) {
            invariant.setQuestion(question.get());
            question.get().getInvariants().add(invariant);
            question.ifPresent(questionRepository::save);
            return question.get();
        }
        return null;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
