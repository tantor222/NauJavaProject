package ru.khamitovma.nauJava.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "test_solution")
public class TestSolution implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "users", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise", nullable = false)
    private Exercise exercise;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<Answer> answer;

    private Integer score;

    private Integer total;

    public TestSolution(User user, Exercise exercise, List<Answer> answer,
                        Integer score, Integer total) {
        this.user = user;
        this.exercise = exercise;
        this.answer = answer;
        this.score = score;
        this.total = total;
    }

    public TestSolution() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestSolution that = (TestSolution) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(exercise, that.exercise) &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(score, that.score) &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, exercise, answer, score, total);
    }
}
