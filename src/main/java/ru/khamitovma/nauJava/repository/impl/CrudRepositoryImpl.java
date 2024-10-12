package ru.khamitovma.nauJava.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.khamitovma.nauJava.model.entity.Exercise;
import ru.khamitovma.nauJava.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@Component
public class CrudRepositoryImpl implements CrudRepository<Exercise, UUID> {

    private final List<Exercise> exerciseContainer;

    @Autowired
    public CrudRepositoryImpl(List<Exercise> exerciseContainer) {
        this.exerciseContainer = exerciseContainer;
    }

    @Override
    public void create(Exercise entity) {
        entity.setId(UUID.randomUUID());
        exerciseContainer.add(entity);
    }

    @Override
    public Exercise read(UUID uuid) {
        return exerciseContainer.stream()
                .filter(exercise -> uuid.equals(exercise.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Exercise> readAll() {
        return exerciseContainer;
    }

    @Override
    public void update(Exercise entity) {
        exerciseContainer.stream()
                .filter(exercise -> entity.getId().equals(exercise.getId()))
                .findFirst()
                .ifPresent(exercise -> {
                    exercise.setDescription(entity.getDescription());
                    exercise.setName(entity.getName());
                    exercise.setQuestions(entity.getQuestions());
                });
    }

    @Override
    public boolean delete(UUID uuid) {
        return exerciseContainer.removeIf(exercise -> uuid.equals(exercise.getId()));
    }
}
