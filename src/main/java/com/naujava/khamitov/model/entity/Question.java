package com.naujava.khamitov.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@SuperBuilder
@Entity
@Table(name = "questions")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Question extends BaseEntity {

    private UUID question;

    private String description;

    private List<String> invariants;

    private String answer;
}