package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDbResultDto;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void addDrugToUserList(String username, Drug drug) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        user.getDrugs().add(drug);
        userRepository.save(user);
        drug.getUsers().add(user);
        drugRepository.save(drug);
    }

    public void removeDrugFromUserList(String username, Drug drug) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        user.getDrugs().remove(drug);
        userRepository.save(user);
        drug.getUsers().remove(user);
        drugRepository.save(drug);
    }

    public List<Drug> getUserDrugs(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return user.getDrugs();
    }


}
