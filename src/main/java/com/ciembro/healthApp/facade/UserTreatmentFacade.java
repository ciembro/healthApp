package com.ciembro.healthApp.facade;

import com.ciembro.healthApp.domain.CreatedUserTreatmentDto;
import com.ciembro.healthApp.domain.UserTreatment;
import com.ciembro.healthApp.domain.UserTreatmentDto;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.mapper.UserTreatmentMapper;
import com.ciembro.healthApp.service.UserService;
import com.ciembro.healthApp.service.UserTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTreatmentFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTreatmentMapper treatmentMapper;

    @Autowired
    private UserTreatmentService treatmentService;

    public CreatedUserTreatmentDto createUserTreatment(UserTreatmentDto treatmentDto)
                                throws UserNotFoundException, DrugNotFoundException {

        UserTreatment treatment = treatmentMapper.mapToUserTreatment(treatmentDto);
        return treatmentMapper.mapToCreatedUserTreatmentDto(
                treatmentService.save(treatment)
        );
    }

    public CreatedUserTreatmentDto updateUserTreatment(CreatedUserTreatmentDto treatmentDto)
                                throws UserNotFoundException, DrugNotFoundException {

        UserTreatment treatment = treatmentMapper.mapToUserTreatment(treatmentDto);
        return treatmentMapper.mapToCreatedUserTreatmentDto(
                treatmentService.save(treatment)
        );
    }

    public void deleteUserTreatment(CreatedUserTreatmentDto treatmentDto)
                                throws UserNotFoundException, DrugNotFoundException {
        UserTreatment treatment = treatmentMapper.mapToUserTreatment(treatmentDto);
        treatmentService.deleteById(treatment.getId());
    }

    public List<CreatedUserTreatmentDto> getAllUserTreatments(String username)
                                throws UserNotFoundException {
        User user = userService.findByUsername(username);
        return treatmentMapper.mapToCreatedUserTreatmentDtoList
                (treatmentService.getAllUserTreatments(user.getId()));
    }

}
