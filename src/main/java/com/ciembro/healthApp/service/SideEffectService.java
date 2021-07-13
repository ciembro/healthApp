package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.sideeffect.SideEffect;
import com.ciembro.healthApp.exception.SideEffectNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.repository.SideEffectRepository;
import com.ciembro.healthApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SideEffectService {

    @Autowired
    private SideEffectRepository sideEffectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrugRepository drugRepository;

    public List<Drug> getUserDrugs(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        List<Long> drugIds = sideEffectRepository.getUserDrugs(user.getId());
         return drugIds.stream()
                .map(id -> drugRepository.findById(id).get())
                .collect(Collectors.toList());
    }

    public SideEffect save(SideEffect sideEffect){
        return sideEffectRepository.save(sideEffect);
    }

    public void removeDrugFromUserList(long sideEffectId){
        sideEffectRepository.removeDrugFromUserList(sideEffectId);
    }

    public void delete(long sideEffectId) throws SideEffectNotFoundException {
        SideEffect sideEffect = sideEffectRepository.findById(sideEffectId).orElseThrow(SideEffectNotFoundException::new);
        sideEffect.setRemoved(true);
        sideEffectRepository.save(sideEffect);
    }

    public List<SideEffect> getSideEffectsByDrugId(String username, long drugId) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return sideEffectRepository.getSideEffectsByDrugId(user.getId(), drugId);
    }
}
