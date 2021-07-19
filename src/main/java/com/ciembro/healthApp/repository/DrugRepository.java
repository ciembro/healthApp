package com.ciembro.healthApp.repository;

import com.ciembro.healthApp.domain.drug.Drug;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface DrugRepository extends CrudRepository<Drug, Long> {

    Drug save(Drug drug);

    List<Drug> findByActiveSubstanceFrag(@Param("frag") String textFragment);

    List<Drug> findByInternationalNameFrag(@Param("frag") String textFragment);

    List<Drug> findByTradeNameFrag(@Param("frag") String textFragment);

    Drug findByUniqueDrugId(int uniqueId);

}
