package ru.khamitovma.nauJava.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.khamitovma.nauJava.model.entity.Exercise;
import ru.khamitovma.nauJava.model.entity.Question;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CommandProcessor {
    private final ExerciseService exerciseService;
    private final Gson g = new Gson();

    @Autowired
    public CommandProcessor(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    public void processCommand(String input) {
        String[] cmd = input.split("__");
        switch (cmd[0]) {
            case "create" -> {
                Exercise exercise = convertJson(cmd[1]);
                if (Objects.nonNull(exercise)) {
                    boolean complete = exerciseService.exerciseCreate(exercise);
                    System.out.printf("Тест %s добавлен%n", complete ? "" : "не");
                }
            }
            case "delete" -> {
                UUID uuid = convertUUID(cmd[1]);
                if (Objects.nonNull(uuid)) {
                    boolean complete = exerciseService.exerciseDelete(uuid);
                    System.out.printf("Тест %s удален%n", complete ? "" : "не");
                }
            }
            case "list" -> {
                List<Exercise> exercises = exerciseService.getListExercises();
                String response = exercises.stream()
                        .map(exercise -> String.format("%s | %s", exercise.getId(), exercise.getDescription()))
                        .collect(Collectors.joining("\n", "", "\n"));
                System.out.printf("id | описание\n" + response);
            }
            case "start" -> {
                UUID uuid = convertUUID(cmd[1]);
                if (Objects.nonNull(uuid)) {
                    Exercise exercise = exerciseService.exerciseStart(uuid);
                    if (Objects.nonNull(exercise)) {
                        System.out.println(g.toJson(exercise));
                    } else {
                        System.out.printf("Тест %s не найден%n", uuid);
                    }
                }
            }
            case "answer" -> {
                Exercise exercise = convertJson(cmd[1]);
                if (Objects.nonNull(exercise)) {
                    String result = exerciseService.exerciseComplete(exercise);
                    System.out.printf(result);
                }
            }
            case "json" -> {
                Exercise exercise = new Exercise(
                        UUID.randomUUID(),
                        "Название теста",
                        "Описание теста",
                        List.of(
                                new Question(
                                        UUID.randomUUID(),
                                        "Текст вопроса",
                                        List.of(
                                                "Ответ 1",
                                                "Ответ 2"
                                        ),
                                        "Ответ 3 - правильный"
                                )
                        )
                );
                System.out.println(g.toJson(exercise));
            }
            default -> System.out.println("Введена неизвестная команда...");
        }
    }

    private Exercise convertJson(String json) {
        try {
            return g.fromJson(json, Exercise.class);
        } catch (Exception e) {
            System.out.println("Объект передан неверно...");
            return null;
        }
    }

    private UUID convertUUID(String uuid) {
        try {
            return UUID.fromString(uuid);
        } catch (Exception e) {
            System.out.println("UUID передан неверно...");
            return null;
        }
    }
}
