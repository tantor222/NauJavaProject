package com.naujava.khamitov.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Вопросы в бланке задания
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private String id;
    private String description;
    private List<String> invariants;
    private String answer;

}
