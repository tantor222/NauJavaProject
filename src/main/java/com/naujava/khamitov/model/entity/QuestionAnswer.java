package com.naujava.khamitov.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Ответ на вопрос в бланке задания
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswer implements Serializable {

    private UUID id;
    private String description;
    private String answer;
    private Integer score;
}
