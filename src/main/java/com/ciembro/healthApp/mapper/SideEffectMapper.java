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

import java.util.List;
import java.util.stream.Collectors;

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

    public SideEffect mapToSideEffect(SideEffectToAddDto sideEffectDto) throws UserNotFoundException, DrugNotFoundException {
        User user = userRepository.findByUsername(sideEffectDto.getUsername()).orElseThrow(UserNotFoundException::new);
        Drug drug = drugRepository.findById(sideEffectDto.getDrugId()).orElseThrow(DrugNotFoundException::new);
        SideEffect sideEffect = new SideEffect();
        sideEffect.setUser(user);
        sideEffect.setDrug(drug);
        sideEffect.setDetails(sideEffectDto.getDetails());
        return sideEffect;
    }

    public List<SideEffectDto> mapToSideEffectDtoList(List<SideEffect> sideEffects){
        return sideEffects.stream()
                .map(this::mapToSideEffectDto)
                .collect(Collectors.toList());
    }
}
