package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.UserTreatment;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.UserTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserTreatmentService {

    @Autowired
    private UserTreatmentRepository treatmentRepository;

    @Autowired
    private UserService userService;

    public UserTreatment save(UserTreatment treatment){
        return treatmentRepository.save(treatment);
    }

    public void deleteById(long treatmentId){
        treatmentRepository.deleteById(treatmentId);
    }

    public List<UserTreatment> getAllUserTreatments(long userId){
        return treatmentRepository.getAllForUser(userId);
    }

    public List<UserTreatment> findAllBetweenDates(LocalDate date, String username) throws UserNotFoundException {
        User user = userService.findByUsername(username);
        return treatmentRepository.findAllBetweenDates(date, user.getId());
    }
}
