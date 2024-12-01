package com.naujava.khamitov.service;

import com.naujava.khamitov.model.dto.AnswerDto;
import com.naujava.khamitov.model.entity.Answer;
import com.naujava.khamitov.model.entity.BaseEntity;
import com.naujava.khamitov.model.entity.Question;
import com.naujava.khamitov.model.entity.QuestionAnswer;
import com.naujava.khamitov.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    public Answer createAnswer(UUID exerciseId, UUID author) {
        return answerRepository.save(Answer.builder()
                .author(author)
                .exercise(exerciseId)
                .questionAnswers(List.of())
                .isCompleted(false)
                .build());
    }

    public Optional<Answer> getAnswerById(UUID id) {
        return answerRepository.findById(id);
    }

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public List<Answer> getAllUserAnswers(UUID authorId) {
        return answerRepository.findAllByAuthor(authorId);
    }

    public Answer submitAnswer(AnswerDto answerDto) {
        Answer answer = getAnswerById(answerDto.getId()).orElseThrow(() -> new RuntimeException("Not found"));
        List<Question> questions = questionService.getAllExerciseQuestions(answer.getExercise());
        List<QuestionAnswer> questionAnswers = calculateQuestionAnswers(answerDto, questions);
        int score = questionAnswers.stream().mapToInt(QuestionAnswer::getScore).sum();
        answer.setQuestionAnswers(questionAnswers);
        answer.setScore(score);
        answer.setCompleted(true);

        return answerRepository.save(answer);
    }

    private List<QuestionAnswer> calculateQuestionAnswers(AnswerDto answerDto, List<Question> questions) {
        Map<UUID, String> answers = questions.stream()
                .collect(Collectors.toMap(BaseEntity::getId, Question::getAnswer));
        return answerDto.getQuestions()
                .stream()
                .map(i -> {
                    UUID id = UUID.fromString(i.getId());
                    return QuestionAnswer.builder()
                            .id(id)
                            .answer(i.getAnswer())
                            .description(i.getDescription())
                            .score(answers.get(id).equals(i.getAnswer()) ? 1 : 0)
                            .build();
                })
                .toList();
    }
}
