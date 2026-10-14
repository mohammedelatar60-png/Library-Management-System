package com.library.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, ID> {
    void save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void delete(ID id);
}