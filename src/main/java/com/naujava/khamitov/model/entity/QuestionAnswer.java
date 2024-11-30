package com.naujava.khamitov.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Data
@RequiredArgsConstructor
public class QuestionAnswer implements Serializable {

    private UUID question;

    private String answer;
}
