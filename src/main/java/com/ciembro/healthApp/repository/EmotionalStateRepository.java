package com.ciembro.healthApp.repository;

import com.ciembro.healthApp.domain.EmotionalState;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmotionalStateRepository extends CrudRepository<EmotionalState, Long> {

    Optional<EmotionalState> findByPlTextAndEngText(String plText, String engText);
}
