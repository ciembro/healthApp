package com.ciembro.healthApp.repository;

import com.ciembro.healthApp.domain.Drug;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface DrugRepository extends CrudRepository<Drug, Long> {

    Drug save(Drug drug);

    List<Drug> findByActiveSubstance(String substance);

    List<Drug> findByActiveSubstanceFrag(@Param("frag") String frag);

    List<Drug> findByCommonNameFrag(@Param("frag") String frag);

    List<Drug> findByTradeNameFrag(@Param("frag") String frag);
}
