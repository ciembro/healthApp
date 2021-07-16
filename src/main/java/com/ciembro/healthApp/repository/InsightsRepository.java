package com.ciembro.healthApp.repository;

import com.ciembro.healthApp.domain.Insights;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InsightsRepository extends CrudRepository<Insights, Long> {

    List<Insights> getAllInsightsByUserId(@Param("userId") long userId);
}
