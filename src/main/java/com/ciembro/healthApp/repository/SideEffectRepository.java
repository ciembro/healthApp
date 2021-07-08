package com.ciembro.healthApp.repository;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.domain.sideeffect.SideEffect;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SideEffectRepository extends CrudRepository<SideEffect, Long> {

    List<Drug> getUserDrugs(@Param("userId") long userId);
}
