package ru.khamitovma.nauJava.model.entity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Вопросы к тестам
 */
public class Question {

    public Question(UUID id, String text, List<String> invariants, String answer) {
        this.id = id;
        this.text = text;
        this.invariants = invariants;
        this.answer = answer;
    }

    /**
     * UUID уникальный идентификатор
     */
    private UUID id;

    /**
     * Текст вопроса
     */
    private String text;

    /**
     * Неверные варианты ответов
     */
    private List<String> invariants;

    /**
     * Верный ответ
     */
    private String answer;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getInvariants() {
        return invariants;
    }

    public void setInvariants(List<String> invariants) {
        this.invariants = invariants;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(text, question.text) &&
                Objects.equals(invariants, question.invariants) &&
                Objects.equals(answer, question.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, invariants, answer);
    }
}
