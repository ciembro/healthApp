package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.sideeffect.SideEffect;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.repository.SideEffectRepository;
import com.ciembro.healthApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public void removeDrugFromUserList(long userId, long drugId){
        sideEffectRepository.removeDrugFromUserList(userId, drugId);
    }

    public void deleteById(long sideEffectId){
        sideEffectRepository.deleteById(sideEffectId);
    }

    public List<SideEffect> getSideEffectsByDrugId(String username, long drugId) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return sideEffectRepository.getSideEffectsByDrugId(user.getId(), drugId);
    }
}
