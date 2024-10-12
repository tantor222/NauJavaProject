package ru.khamitovma.nauJava.service;

import ru.khamitovma.nauJava.model.entity.Exercise;

import java.util.List;
import java.util.UUID;

public interface ExerciseService {

    /**
     * Создать вариант теста
     * @param exercise  Exercise
     * @return          boolean
     */
    boolean exerciseCreate(Exercise exercise);

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

    /**
     * Начать тест
     * @param id    UUID
     * @return      Exercise
     */
    Exercise exerciseStart(UUID id);

    /**
     * Закончить тест и получить результаты
     * @param exercise  Exercise
     * @return          String
     */
    String exerciseComplete(Exercise exercise);

}
