package com.naujava.khamitov.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Ответ с ошибкой
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestExceptionDto {

    private String message;

    public static RestExceptionDto create(Throwable e) {
        return new RestExceptionDto(e.getMessage());
    }
    public static RestExceptionDto create(String message) {
        return new RestExceptionDto(message);
    }
}