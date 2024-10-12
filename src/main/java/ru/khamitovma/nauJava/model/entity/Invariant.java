package ru.khamitovma.nauJava.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.gson.annotations.Expose;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "invariant")
public class Invariant implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    @JsonBackReference
    @Expose
    private Question question;

    private String description;
    private Integer score;

    public Invariant(Question question, String description, Integer score) {
        this.question = question;
        this.description = description;
        this.score = score;
    }

    public Invariant() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invariant invariant = (Invariant) o;
        return Objects.equals(id, invariant.id) &&
                Objects.equals(question, invariant.question) &&
                Objects.equals(description, invariant.description) &&
                Objects.equals(score, invariant.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, description, score);
    }
}