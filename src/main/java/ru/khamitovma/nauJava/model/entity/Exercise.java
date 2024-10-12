package ru.khamitovma.nauJava.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Варианты тестов
 */
@Entity
@Table(name = "exercise")
public class Exercise implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String description;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "exercise", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ExerciseQuestion> questions;
    @Transient
    private List<UUID> questionsUpdate;

    public Exercise(String name, String description, List<ExerciseQuestion> questions) {
        this.name = name;
        this.description = description;
        this.questions = questions;
    }

    public Exercise(String name,
                    String description,
                    List<ExerciseQuestion> ignored,
                    List<UUID> questionsUpdate) {
        this.name = name;
        this.description = description;
        this.questionsUpdate = questionsUpdate;
    }

    public Exercise() {}

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

    public List<ExerciseQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ExerciseQuestion> questions) {
        this.questions = questions;
    }

    public List<UUID> getQuestionsUpdate() {
        return questionsUpdate;
    }

    public void setQuestionsUpdate(List<UUID> questionsUpdate) {
        this.questionsUpdate = questionsUpdate;
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
