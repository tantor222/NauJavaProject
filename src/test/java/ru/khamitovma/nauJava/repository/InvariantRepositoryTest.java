package ru.khamitovma.nauJava.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.khamitovma.nauJava.model.entity.Invariant;
import ru.khamitovma.nauJava.model.entity.Question;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class InvariantRepositoryTest {

    private final InvariantRepository invariantRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    InvariantRepositoryTest(InvariantRepository invariantRepository,
                                   QuestionRepository questionRepository) {
        this.invariantRepository = invariantRepository;
        this.questionRepository = questionRepository;
    }

    @Test
    void testFindByScoreBetweenTest() {
        Invariant invariant = new Invariant();
        invariant.setScore(5);
        invariant.setQuestion(null);
        invariant.setDescription("desc");
        invariantRepository.save(invariant);

        List<Invariant> invariantsFound = invariantRepository.findByScoreBetween(4, 6);

        Assertions.assertNotNull(invariantsFound);
        Assertions.assertEquals(invariantsFound.size(), 1);
    }

    @Test
    void testFindAllQuestionInvariants() {

        Question q = new Question();
        q.setInvariants(new ArrayList<>());
        Invariant invariant = new Invariant();
        invariant.setQuestion(q);
        invariant.setDescription("desc");
        invariant.setScore(0);
        q.getInvariants().add(invariant);
        Question question = questionRepository.save(q);

        List<Invariant> invariantsFound = invariantRepository.findAllQuestionInvariants(question.getId());

        Assertions.assertNotNull(invariantsFound);
        Assertions.assertEquals(invariantsFound.size(), 1);
    }
}
