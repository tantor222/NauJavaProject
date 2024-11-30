package com.naujava.khamitov.service;

import com.naujava.khamitov.model.entity.Exercise;
import com.naujava.khamitov.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public Exercise getById(UUID id) {
        return exerciseRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise updateExercise(UUID id, String title, String description) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isEmpty()) {
            throw new RuntimeException("ss");
        }
        Exercise entity = exercise.get();
        entity.setTitle(title);
        entity.setDescription(description);
        return exerciseRepository.save(entity);
    }
}
