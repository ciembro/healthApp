package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.sideeffect.SideEffect;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.repository.SideEffectRepository;
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
    private final SideEffectService sideEffectService;

    public void saveAll(List<Drug> drugs){
        for (Drug drug : drugs){
            drugRepository.save(drug);
        }
    }

    public Drug save(Drug drug){
        return drugRepository.save(drug);
    }

    public List<Drug> findByActiveSubstanceFrag(String substanceNameFrag){
        return drugRepository.findByActiveSubstanceFrag(substanceNameFrag);
    }
    public List<Drug> findByCommonNameFrag(String commonNameFrag){
        return drugRepository.findByCommonNameFrag(commonNameFrag);
    }

    public List<Drug> findByTradeNameFrag(String tradeNameFrag){
        return drugRepository.findByTradeNameFrag(tradeNameFrag);
    }

    public List<Drug> findAllMatching(String textToMatch){
        List<Drug> matchedDrugs = new ArrayList<>();
        matchedDrugs.addAll(findByActiveSubstanceFrag(textToMatch));
        matchedDrugs.addAll(findByCommonNameFrag(textToMatch));
        matchedDrugs.addAll(findByTradeNameFrag(textToMatch));
        return matchedDrugs;
    }

    public String getLeafletUrl(Drug drug){
        return drug.getLeafletUrl();
    }

    public SideEffect addDrugToUserList(String username, Drug drug) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        SideEffect sideEffect = new SideEffect();
        sideEffect.setUser(user);
        sideEffect.setDrug(drug);
        return sideEffectService.save(sideEffect);
    }

    public void removeDrugFromUserList(String username, Drug drug) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        sideEffectService.removeDrugFromUserList(user.getId(), drug.getId());
    }






}
