package com.naujava.khamitov.controller;

import com.naujava.khamitov.model.dto.RestExceptionDto;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(java.lang.Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestExceptionDto exception(java.lang.Exception e) {
        return RestExceptionDto.create(e);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String exception(NoResourceFoundException e) {
        return "404";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exception(ResourceNotFoundException e) {
        return "404";
    }
}