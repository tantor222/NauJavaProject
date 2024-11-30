package com.naujava.khamitov.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Entity
@Table(name = "exercise")
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Exercise extends BaseEntity {

    private UUID author;

    private String description;

}
