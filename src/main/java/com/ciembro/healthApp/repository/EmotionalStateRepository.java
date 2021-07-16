package com.ciembro.healthApp.repository;

import com.ciembro.healthApp.domain.EmotionalState;
import org.springframework.data.repository.CrudRepository;

public interface EmotionalStateRepository extends CrudRepository<EmotionalState, Long> {
}
