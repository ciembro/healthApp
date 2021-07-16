package com.ciembro.healthApp.repository;

import com.ciembro.healthApp.domain.EmotionalState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmotionalStateRepository extends CrudRepository<EmotionalState, Long> {


    EmotionalState findByEngText(String text);

}
