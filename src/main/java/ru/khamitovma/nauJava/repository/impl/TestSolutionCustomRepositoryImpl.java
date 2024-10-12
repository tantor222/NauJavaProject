package ru.khamitovma.nauJava.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.khamitovma.nauJava.model.entity.Exercise;
import ru.khamitovma.nauJava.model.entity.TestSolution;
import ru.khamitovma.nauJava.repository.TestSolutionCustomRepository;

import java.util.List;

@Repository
public class TestSolutionCustomRepositoryImpl implements TestSolutionCustomRepository {

    private final EntityManager entityManager;
    @Autowired
    public TestSolutionCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TestSolution> findByScore(Integer score) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TestSolution> criteriaQuery = criteriaBuilder.createQuery(TestSolution.class);
        Root<TestSolution> testSolutionRoot = criteriaQuery.from(TestSolution.class);
        Predicate scorePredicate = criteriaBuilder.equal(testSolutionRoot.get("score"), score);
        criteriaQuery.select(testSolutionRoot).where(scorePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<TestSolution> findByExerciseName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TestSolution> criteriaQuery = criteriaBuilder.createQuery(TestSolution.class);
        Root<TestSolution> testSolutionRoot = criteriaQuery.from(TestSolution.class);
        Join<TestSolution, Exercise> exercise = testSolutionRoot.join("exercise", JoinType.INNER);
        Predicate namePredicate = criteriaBuilder.equal(exercise.get("name"), name);
        criteriaQuery.select(testSolutionRoot).where(namePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
