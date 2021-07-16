package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.UserTreatment;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.repository.UserRepository;
import com.ciembro.healthApp.repository.UserTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTreatmentService {

    @Autowired
    private UserTreatmentRepository treatmentRepository;

    public UserTreatment save(UserTreatment treatment){
        return treatmentRepository.save(treatment);
    }

    public void deleteById(long treatmentId){
        treatmentRepository.deleteById(treatmentId);
    }

    public List<UserTreatment> getAllUserTreatments(long userId){
        return treatmentRepository.getAllForUser(userId);

    }
}
