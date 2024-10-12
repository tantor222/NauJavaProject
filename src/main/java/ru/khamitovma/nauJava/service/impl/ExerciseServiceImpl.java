package ru.khamitovma.nauJava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khamitovma.nauJava.model.entity.Exercise;
import ru.khamitovma.nauJava.model.entity.Question;
import ru.khamitovma.nauJava.repository.CrudRepository;
import ru.khamitovma.nauJava.service.ExerciseService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final CrudRepository<Exercise, UUID> crudRepository;

    @Autowired
    public ExerciseServiceImpl(CrudRepository<Exercise, UUID> crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public boolean exerciseCreate(Exercise exercise) {
        crudRepository.create(exercise);
        return true;
    }

    @Override
    public boolean exerciseDelete(UUID id) {
        return crudRepository.delete(id);
    }

    @Override
    public List<Exercise> getListExercises() {
        return crudRepository.readAll();
    }

    @Override
    public Exercise exerciseStart(UUID id) {
        Exercise exercise = crudRepository.read(id);
        if (Objects.nonNull(exercise)) {
            return new Exercise(
                    exercise.getId(),
                    exercise.getName(),
                    exercise.getDescription(),
                    exercise.getQuestions()
                            .stream()
                            .map(question -> new Question(
                                        question.getId(),
                                        question.getText(),
                                        Stream.concat(
                                                question.getInvariants().stream(),
                                                Stream.of(question.getAnswer())
                                        ).toList(),
                                        ""
                                    )
                            )
                            .toList()
            );

        }
        return null;
    }

    @Override
    public String exerciseComplete(Exercise exercise) {
        Map<UUID, String> answers = exercise.getQuestions()
                .stream()
                .collect(Collectors.toMap(Question::getId, Question::getAnswer));

        Exercise test = crudRepository.read(exercise.getId());
        if (Objects.isNull(test)) {
            return "Тест не найден";
        }
        long positive = test.getQuestions().stream()
                .filter(question -> answers.containsKey(question.getId()) &&
                        question.getAnswer().equals(answers.get(question.getId())))
                .count();
        return String.format("Правильных ответов %s из %s", positive, test.getQuestions().size());
    }
}
