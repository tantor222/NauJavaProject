package ru.khamitovma.nauJava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khamitovma.nauJava.model.entity.Invariant;
import ru.khamitovma.nauJava.repository.InvariantRepository;
import ru.khamitovma.nauJava.service.InvariantService;

import java.util.Optional;
import java.util.UUID;

@Service
public class InvariantServiceImpl implements InvariantService {

    private final InvariantRepository invariantRepository;

    @Autowired
    public InvariantServiceImpl(InvariantRepository invariantRepository) {
        this.invariantRepository = invariantRepository;
    }

    @Override
    public void createInvariant(Invariant invariant) {
        invariantRepository.save(invariant);
    }

    @Override
    public void removeInvariant(UUID uuid) {
        Optional<Invariant> invariant = invariantRepository.findById(uuid);
        invariant.ifPresent(invariantRepository::delete);

    }
}
