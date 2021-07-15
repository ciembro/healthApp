package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.sideeffect.SideEffect;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class DrugService {
    private final DrugRepository drugRepository;
    private final UserRepository userRepository;


    public Drug save(Drug drug){
        return drugRepository.save(drug);
    }

    public Drug findById(Long id) throws DrugNotFoundException {
        return drugRepository.findById(id).orElseThrow(DrugNotFoundException::new);
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

    public String getLeafletUrl(Drug drug){
        return drug.getLeafletUrl();
    }

    public Drug findByUniqueDrugId(int uniqueId){
        return drugRepository.findByUniqueDrugId(uniqueId);
    }

    public void addDrugToUserList(String username, Drug drug) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        //TODO
    }

    public void removeDrugFromUserList(String username, Drug drug) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        //TODO
    }






}
