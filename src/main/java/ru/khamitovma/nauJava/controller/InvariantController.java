package ru.khamitovma.nauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.khamitovma.nauJava.model.entity.Invariant;
import ru.khamitovma.nauJava.repository.InvariantRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("rest/invariant")
public class InvariantController {

    private final InvariantRepository invariantRepository;

    @Autowired
    public InvariantController(InvariantRepository invariantRepository) {
        this.invariantRepository = invariantRepository;
    }

    @GetMapping
    public ResponseEntity<List<Invariant>> findByScoreBetween(
            @RequestParam Integer start,
            @RequestParam Integer end
    ) {
        return ResponseEntity.ok(invariantRepository.findByScoreBetween(start, end));
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<List<Invariant>> findAllQuestionInvariants(
            @PathVariable UUID questionId
    ) {
        return ResponseEntity.ok(invariantRepository.findAllQuestionInvariants(questionId));
    }
}
