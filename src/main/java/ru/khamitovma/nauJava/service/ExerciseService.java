package ru.khamitovma.nauJava.service;

import ru.khamitovma.nauJava.model.entity.Exercise;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExerciseService {

    /**
     * Создать вариант теста
     * @param exercise  Exercise
     * @return          boolean
     */
    Exercise exerciseCreate(Exercise exercise);

    /**
     * Удалить вариант теста
     * @param id    UUID
     * @return      boolean
     */
    boolean exerciseDelete(UUID id);

    /**
     * Получить все варианты тестов
     * @return  List<Exercise>
     */
    List<Exercise> getListExercises();

    Optional<Exercise> findExercise(UUID uuid);

}
