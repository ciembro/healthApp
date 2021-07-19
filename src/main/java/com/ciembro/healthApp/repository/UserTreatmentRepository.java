package com.ciembro.healthApp.repository;

import com.ciembro.healthApp.domain.UserTreatment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserTreatmentRepository extends CrudRepository<UserTreatment, Long> {

    @Query(nativeQuery = true)
    List<UserTreatment> getAllForUser(@Param("userId") long userId);

    List<UserTreatment> findAllBetweenDates(@Param("date") LocalDate date,
                                            @Param("userId") long userId);

}
