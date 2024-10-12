package ru.khamitovma.nauJava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.khamitovma.nauJava.model.entity.Exercise;
import ru.khamitovma.nauJava.model.entity.ExerciseQuestion;
import ru.khamitovma.nauJava.model.entity.Question;
import ru.khamitovma.nauJava.repository.ExerciseRepository;
import ru.khamitovma.nauJava.service.ExerciseService;
import ru.khamitovma.nauJava.service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final QuestionService questionService;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository,
                               QuestionService questionService,
                               PlatformTransactionManager platformTransactionManager) {
        this.exerciseRepository = exerciseRepository;
        this.questionService = questionService;
        this.transactionManager = platformTransactionManager;
    }

    @Override
    public Exercise exerciseCreate(Exercise exercise) {
        TransactionStatus status = transactionManager.getTransaction(new
                DefaultTransactionDefinition());
        try {
            Exercise entity = new Exercise();
            entity.setName(exercise.getName());
            entity.setDescription(exercise.getDescription());
            entity.setQuestions(new ArrayList<>());
            if (Objects.nonNull(exercise.getQuestionsUpdate()) && !exercise.getQuestionsUpdate().isEmpty()) {
                for (UUID uuid : exercise.getQuestionsUpdate()) {
                    Optional<Question> question = questionService.findQuestion(uuid);
                    question.ifPresent(value -> entity.getQuestions().add(new ExerciseQuestion(
                            entity,
                            value
                    )));
                }
            }
            Exercise result = exerciseRepository.save(entity);
            transactionManager.commit(status);
            return result;
        } catch (Exception ex) {
            transactionManager.rollback(status);
            throw ex;
        }

    }

    @Override
    public boolean exerciseDelete(UUID id) {
        Optional<Exercise> exists = exerciseRepository.findById(id);
        if (exists.isPresent()) {
            exerciseRepository.delete(exists.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Exercise> getListExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public Optional<Exercise> findExercise(UUID uuid) {
        return exerciseRepository.findById(uuid);
    }
}
