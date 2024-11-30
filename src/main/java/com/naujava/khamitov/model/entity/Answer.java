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
@Table(name = "answers")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Answer extends BaseEntity {

    private UUID author;

    private UUID exercise;

    private List<QuestionAnswer> questionAnswers;
}