package ru.khamitovma.nauJava.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.khamitovma.nauJava.model.entity.Exercise;
import ru.khamitovma.nauJava.model.entity.Invariant;
import ru.khamitovma.nauJava.model.entity.Question;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CommandProcessor {
    private final ExerciseService exerciseService;
    private final QuestionService questionService;
    private final InvariantService invariantService;
    private final UserService userService;
    private  final ObjectMapper mapper = new ObjectMapper();
    private final Gson g = new Gson();

    @Autowired
    public CommandProcessor(ExerciseService exerciseService, QuestionService questionService,
                            InvariantService invariantService, UserService userService) {
        this.exerciseService = exerciseService;
        this.questionService = questionService;
        this.invariantService = invariantService;
        this.userService = userService;
    }

    public void processCommand(String input) {
        String[] cmd = input.split("__");
        switch (cmd[0]) {
            case "create_question_json" -> {
                var question = new Question(
                        "Текст вопроса",
                        List.of(
                                new Invariant(null, "Неверный 1", 0),
                                new Invariant(null, "Неверный 2", 0),
                                new Invariant(null, "Верный", 1)
                        )
                );
                System.out.println(g.toJson(question));
            }
            case "create_question" -> {
                Question question = convertJson(cmd[1], Question.class);
                if (Objects.nonNull(question)) {
                    System.out.println(questionService.createQuestion(question));
                }
            }
            case "get_questions" -> {
                List<Question> questions = questionService.getAllQuestions();
                System.out.println(questions.stream()
                        .map(this::toJson)
                        .collect(Collectors.joining("\n")));
            }
            case "create_invariant_json" -> {
                Invariant invariant = new Invariant(null, "Неверный 1", 0);
                System.out.println(g.toJson(invariant));

            }
            case "create_invariant" -> {
                UUID uuid = convertUUID(cmd[1]);
                Invariant invariant = convertJson(cmd[2], Invariant.class);
                if (Objects.nonNull(uuid) && Objects.nonNull(invariant)) {
                    Question question = questionService.addInvariant(uuid, invariant);
                    System.out.println(g.toJson(question));
                }
            }
            case "remove_invariant" -> {
                UUID uuid = convertUUID(cmd[1]);
                if (Objects.nonNull(uuid)) {
                    invariantService.removeInvariant(uuid);
                    System.out.println("OK");
                }
            }
            case "create_exercise_json" -> {
                Exercise exercise = new Exercise(
                        "Название теста",
                        "Описание теста",
                        null,
                        List.of(new UUID(0, 0))
                );
                System.out.println(g.toJson(exercise));
            }
            case "create_exercise" -> {
                Exercise exercise = convertJson(cmd[1], Exercise.class);
                if (Objects.nonNull(exercise)) {
                    System.out.println(exerciseService.exerciseCreate(exercise));
                }
            }
            case "exercise_add_question" -> {
                System.out.println("Not implemented...");
            }
            case "exercise_remove_question" -> {
                System.out.println("Not implemented...");
            }
            case "create_user" -> {
                System.out.println(g.toJson(userService.createUser()));
            }
            case "test" -> {
                // TODO
            }
            case "answer" -> {
                // TODO
            }
            default -> System.out.println("Введена неизвестная команда...");
        }
    }

    private <T, E extends Class<T>> T convertJson(String json, E cl) {
        try {
            return g.fromJson(json, cl);
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

    private String toJson(Object val) {
        try {
            return mapper.writeValueAsString(val);
        } catch (Exception e) {
            return "Error::mapping";
        }
    }
}
