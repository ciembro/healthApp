package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.UserTreatment;
import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class DrugService {
    private final DrugRepository drugRepository;
    private final UserRepository userRepository;
    private final UserTreatmentService userTreatmentService;

    public Drug save(Drug drug){
        return drugRepository.save(drug);
    }

    public List<Drug> findByActiveSubstanceFrag(String substanceNameFrag){
        return drugRepository.findByActiveSubstanceFrag(substanceNameFrag);
    }
    public List<Drug> findByInternationalNameFrag(String commonNameFrag){
        return drugRepository.findByInternationalNameFrag(commonNameFrag);
    }

    public List<Drug> findByTradeNameFrag(String tradeNameFrag){
        return drugRepository.findByTradeNameFrag(tradeNameFrag);
    }

    public List<Drug> findAllMatching(String textToMatch){
        List<Drug> matchedDrugs = new ArrayList<>();
        matchedDrugs.addAll(findByActiveSubstanceFrag(textToMatch));
        matchedDrugs.addAll(findByInternationalNameFrag(textToMatch));
        matchedDrugs.addAll(findByTradeNameFrag(textToMatch));
        return matchedDrugs;
    }

    public Drug findByUniqueDrugId(int uniqueId){
        return drugRepository.findByUniqueDrugId(uniqueId);
    }

    public List<Drug> findAllByDate(LocalDate date, String username)
            throws UserNotFoundException {
        List<UserTreatment> treatments = userTreatmentService
                .findAllBetweenDates(date, username);
        return treatments.stream()
                .map(UserTreatment::getDrug)
                .collect(Collectors.toList());
    }

    public Set<Drug> getAllByUser(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        List<UserTreatment> treatments = userTreatmentService.getAllUserTreatments(user.getId());
        return treatments.stream()
                .map(UserTreatment::getDrug)
                .collect(Collectors.toSet());
    }


}
