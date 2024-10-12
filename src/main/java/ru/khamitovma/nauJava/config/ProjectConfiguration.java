package ru.khamitovma.nauJava.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.khamitovma.nauJava.model.entity.Exercise;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProjectConfiguration {

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Exercise> exerciseContainer() {
        return new ArrayList<>();
    }
}
