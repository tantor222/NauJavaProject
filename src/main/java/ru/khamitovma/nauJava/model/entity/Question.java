package ru.khamitovma.nauJava.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Вопросы к тестам
 */
@Entity
@Table(name = "question")
public class Question implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Invariant> invariants = new ArrayList<>();

    public Question(String description, List<Invariant> invariants) {
        this.description = description;
        this.invariants = invariants;
    }

    public Question() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Invariant> getInvariants() {
        return invariants;
    }

    public void setInvariants(List<Invariant> invariants) {
        this.invariants = invariants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(description, question.description) && Objects.equals(invariants, question.invariants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, invariants);
    }
}