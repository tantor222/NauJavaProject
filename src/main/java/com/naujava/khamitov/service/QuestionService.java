package com.naujava.khamitov.service;

import com.naujava.khamitov.model.dto.QuestionDto;
import com.naujava.khamitov.model.entity.Answer;
import com.naujava.khamitov.model.entity.Question;
import com.naujava.khamitov.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getAllExerciseQuestions(UUID exerciseId) {
        return questionRepository.findAllByExercise(exerciseId);
    }

    public List<QuestionDto> getAllAnswerQuestions(Answer answer) {
        if (!CollectionUtils.isEmpty(answer.getQuestionAnswers())) {
            return answer.getQuestionAnswers()
                    .stream()
                    .map(i -> QuestionDto.builder()
                            .id(i.getId().toString())
                            .description(i.getDescription())
                            .invariants(List.of(i.getAnswer()))
                            .score(i.getScore())
                            .build())
                    .toList();
        }
        return getAllExerciseQuestions(answer.getExercise())
                .stream()
                .map(i -> {
                    List<String> invariants = new ArrayList<>(Stream.concat(i.getInvariants().stream(),
                            Stream.of(i.getAnswer())).toList());
                    Collections.shuffle(invariants);
                    return QuestionDto.builder()
                            .id(i.getId().toString())
                            .description(i.getDescription())
                            .invariants(invariants)
                            .build();
                })
                .toList();
    }

    public void createQuestion(Question question) {
        questionRepository.save(question);
    }
}
