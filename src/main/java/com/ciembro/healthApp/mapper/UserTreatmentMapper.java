package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.CreatedUserTreatmentDto;
import com.ciembro.healthApp.domain.UserTreatment;
import com.ciembro.healthApp.domain.UserTreatmentDto;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserTreatmentMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private DrugMapper drugMapper;

    public UserTreatment mapToUserTreatment(UserTreatmentDto dto) throws UserNotFoundException, DrugNotFoundException {
        User user = userRepository.findByUsername(dto.getUsername()).orElseThrow(UserNotFoundException::new);
        UserTreatment userTreatment = new UserTreatment();
        userTreatment.setUser(user);
        userTreatment.setDrug(drugMapper.mapToDrug(dto.getDrugDto()));
        userTreatment.setStartedAt(dto.getStartedAt());
        userTreatment.setFinishedAt(dto.getFinishedAt());
        return userTreatment;
    }

    public UserTreatment mapToUserTreatment(CreatedUserTreatmentDto dto) throws UserNotFoundException, DrugNotFoundException {
        User user = userRepository.findByUsername(dto.getUsername()).orElseThrow(UserNotFoundException::new);
        UserTreatment userTreatment = new UserTreatment();
        userTreatment.setId(dto.getId());
        userTreatment.setUser(user);
        userTreatment.setDrug(drugMapper.mapToDrug(dto.getDrugDto()));
        userTreatment.setStartedAt(dto.getStartedAt());
        userTreatment.setFinishedAt(dto.getFinishedAt());
        return userTreatment;
    }

    public CreatedUserTreatmentDto mapToCreatedUserTreatmentDto(UserTreatment treatment){
        CreatedUserTreatmentDto dto = new CreatedUserTreatmentDto();
        dto.setId(treatment.getId());
        dto.setUsername(treatment.getUser().getUsername());
        dto.setDrugDto(drugMapper.mapToDrugDto(treatment.getDrug()));
        dto.setStartedAt(treatment.getStartedAt());
        dto.setFinishedAt(treatment.getFinishedAt());
        return dto;
    }

    public List<CreatedUserTreatmentDto> mapToCreatedUserTreatmentDtoList(List<UserTreatment> treatments){
        return treatments.stream()
                .map(this::mapToCreatedUserTreatmentDto)
                .collect(Collectors.toList());
    }

}
