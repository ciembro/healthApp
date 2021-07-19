package com.ciembro.healthApp.repository;

import com.ciembro.healthApp.domain.EmotionalState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmotionalStateRepository extends CrudRepository<EmotionalState, Long> {


    EmotionalState findByEngText(String text);


    Optional<EmotionalState> findByPlTextAndEngText(String plText, String engText);
}
