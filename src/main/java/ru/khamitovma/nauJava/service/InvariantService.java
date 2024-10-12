package ru.khamitovma.nauJava.service;

import ru.khamitovma.nauJava.model.entity.Invariant;

import java.util.UUID;

public interface InvariantService {

    void createInvariant(Invariant invariant);

    void removeInvariant(UUID uuid);
}
