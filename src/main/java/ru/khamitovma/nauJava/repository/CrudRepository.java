package ru.khamitovma.nauJava.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrudRepository<T, ID> {
    void create(T entity);
    T read(ID id);
    List<T> readAll();
    void update(T entity);
    boolean delete(ID id);
}
