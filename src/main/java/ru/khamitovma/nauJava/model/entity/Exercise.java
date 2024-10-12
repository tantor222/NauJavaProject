package ru.khamitovma.nauJava.model.entity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Варианты тестов
 */
public class Exercise {

    public Exercise(UUID id, String name, String description, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.questions = questions;
    }

    /**
     * UUID уникальный идентификатор
     */
    private UUID id;

    /**
     * short name
     */
    private String name;

    /**
     * Описание
     */
    private String description;

    /**
     * Вопросы
     */
    private List<Question> questions;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(id, exercise.id) &&
                Objects.equals(name, exercise.name) &&
                Objects.equals(description, exercise.description) &&
                Objects.equals(questions, exercise.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, questions);
    }
}
