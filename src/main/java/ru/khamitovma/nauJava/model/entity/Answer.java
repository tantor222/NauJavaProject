package ru.khamitovma.nauJava.model.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Answer implements Serializable {

    private UUID question;
    private UUID invariant;

    public Answer(UUID question, UUID invariant) {
        this.question = question;
        this.invariant = invariant;
    }

    public Answer() {}

    public UUID getQuestion() {
        return question;
    }

    public void setQuestion(UUID question) {
        this.question = question;
    }

    public UUID getInvariant() {
        return invariant;
    }

    public void setInvariant(UUID invariant) {
        this.invariant = invariant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(question, answer.question) &&
                Objects.equals(invariant, answer.invariant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, invariant);
    }
}
