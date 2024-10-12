package ru.khamitovma.nauJava.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.khamitovma.nauJava.model.entity.Exercise;
import ru.khamitovma.nauJava.model.entity.TestSolution;
import ru.khamitovma.nauJava.model.entity.User;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class TestSolutionCustomRepositoryTest {

    private final TestSolutionCustomRepository testSolutionCustomRepository;
    private final TestSolutionRepository testSolutionRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public TestSolutionCustomRepositoryTest(TestSolutionCustomRepository testSolutionCustomRepository,
                                            TestSolutionRepository testSolutionRepository,
                                            UserRepository userRepository,
                                            ExerciseRepository exerciseRepository) {
        this.testSolutionCustomRepository = testSolutionCustomRepository;
        this.testSolutionRepository = testSolutionRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Test
    public void testFindScore() {
        int testScore = 5;
        User user = userRepository.save(new User("name"));
        TestSolution entity = new TestSolution();
        entity.setUser(user);
        Exercise exerciseEntity = new Exercise();
        Exercise exercise = exerciseRepository.save(exerciseEntity);
        entity.setExercise(exercise);
        entity.setScore(testScore);
        testSolutionRepository.save(entity);

        List<TestSolution> found = testSolutionCustomRepository.findByScore(testScore);
        Assertions.assertFalse(found.isEmpty());
    }

    @Test
    public void testFindByExerciseName() {
        User user = userRepository.save(new User("name"));
        String testName = "test";
        Exercise exerciseEntity = new Exercise();
        exerciseEntity.setName(testName);
        Exercise exercise = exerciseRepository.save(exerciseEntity);

        TestSolution testSolution = new TestSolution();
        testSolution.setExercise(exercise);
        testSolution.setUser(user);
        testSolutionRepository.save(testSolution);

        List<TestSolution> found = testSolutionCustomRepository.findByExerciseName(testName);
        Assertions.assertFalse(found.isEmpty());
    }
}
