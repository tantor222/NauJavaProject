package com.naujava.khamitov.service;

import com.naujava.khamitov.model.entity.Question;
import com.naujava.khamitov.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getAllExerciseQuestions(UUID exerciseId) {
        return questionRepository.findAllByExercise(exerciseId);
    }

    public void createQuestion(Question question) {
        questionRepository.save(question);
    }
}
