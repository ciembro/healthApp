package com.ciembro.healthApp.repository;

import com.ciembro.healthApp.domain.SideEffect;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SideEffectRepository extends CrudRepository<SideEffect, Long> {

    Optional<SideEffect> findByText(String text);
}
