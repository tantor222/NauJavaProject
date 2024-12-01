package com.naujava.khamitov.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

    private UUID id;
    private String title;
    private List<QuestionDto> questions;
    private Integer score;
    private boolean isCompleted;

}
