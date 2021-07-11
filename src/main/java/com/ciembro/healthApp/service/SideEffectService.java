package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.sideeffect.SideEffectDto;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.sideeffect.SideEffect;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.repository.SideEffectRepository;
import com.ciembro.healthApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return sideEffectRepository.getUserDrugs(user.getId());
    }

    public SideEffect save(SideEffect sideEffect){
        return sideEffectRepository.save(sideEffect);
    }

    void removeDrugFromUserList(long userId, long drugId){
        sideEffectRepository.removeDrugFromUserList(userId, drugId);
    }
}
