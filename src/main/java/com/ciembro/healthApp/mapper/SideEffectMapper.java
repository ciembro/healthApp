package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.sideeffect.*;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.repository.SideEffectRepository;
import com.ciembro.healthApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SideEffectMapper {

    @Autowired
    public SideEffectRepository sideEffectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrugRepository drugRepository;

    public SideEffect mapToSideEffect(SideEffectDto sideEffectDto) throws UserNotFoundException, DrugNotFoundException {
        User user = userRepository.findByUsername(sideEffectDto.getUsername()).orElseThrow(UserNotFoundException::new);
        Drug drug = drugRepository.findById(sideEffectDto.getDrugId()).orElseThrow(DrugNotFoundException::new);
        return new SideEffect(sideEffectDto.getId(),
                user,
                drug,
                sideEffectDto.getDetails());

    }

    public SideEffectDto mapToSideEffectDto(SideEffect sideEffect){

        return new SideEffectDto(sideEffect.getId(),
                sideEffect.getUser().getUsername(),
                sideEffect.getDrug().getId(),
                sideEffect.getDetails());
    }
}
