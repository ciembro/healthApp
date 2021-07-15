package com.ciembro.healthApp.repository;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.domain.sideeffect.SideEffect;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface SideEffectRepository extends CrudRepository<SideEffect, Long> {

    List<Long> getUserDrugs(@Param("userId") long userId);

    @Modifying
    @Query(nativeQuery = true)
    void removeDrugFromUserList(@Param("userId") long userId, @Param("drugId") long drugId);

    @Query(nativeQuery = true)
    List<SideEffect> getSideEffectsByDrugId (@Param("userId") long userId, @Param("drugId") long drugId);
}
