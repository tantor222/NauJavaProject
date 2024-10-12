package ru.khamitovma.nauJava.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.gson.annotations.Expose;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "exercise_questions")
public class ExerciseQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise", nullable = false)
    @Expose
    @JsonBackReference
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "question", nullable = false)
    private Question question;

    public ExerciseQuestion(Exercise exercise, Question question) {
        this.exercise = exercise;
        this.question = question;
    }

    public ExerciseQuestion(Question question) {
        this.question = question;
    }

    public ExerciseQuestion() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseQuestion that = (ExerciseQuestion) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(exercise, that.exercise) &&
                Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exercise, question);
    }
}