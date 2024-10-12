package ru.khamitovma.nauJava.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.khamitovma.nauJava.model.entity.Exercise;
import ru.khamitovma.nauJava.model.entity.Question;
import ru.khamitovma.nauJava.repository.QuestionRepository;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ExerciseServiceTest {

    private final ExerciseService exerciseService;
    private final QuestionRepository questionRepository;

    @Autowired
    public ExerciseServiceTest(ExerciseService exerciseService, QuestionRepository questionRepository) {
        this.exerciseService = exerciseService;
        this.questionRepository = questionRepository;
    }

    @Test
    public void testExerciseCreateTransactionSuccess() {
        List<Question> questions = questionRepository
                .saveAll(List.of(new Question(), new Question(), new Question()));

        Exercise exercise = new Exercise();
        exercise.setName("name");
        exercise.setDescription("Desc");
        exercise.setQuestionsUpdate(questions.stream().map(Question::getId).toList());
        Exercise result = exerciseService.exerciseCreate(exercise);
        Assertions.assertNotNull(result);
    }

    @Test
    public void testExerciseCreateTransactionFailed() {
        // TODO
    }
}
