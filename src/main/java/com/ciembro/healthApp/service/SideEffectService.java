package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.SideEffect;
import com.ciembro.healthApp.repository.SideEffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
public class SideEffectService {

    @Autowired
    SideEffectRepository repository;

    public SideEffect save(SideEffect sideEffect){
        return repository.save(sideEffect);
    }

    public List<SideEffect> findAll(){
        return (List<SideEffect>)repository.findAll();
    }
    public void loadSideEffects(){

            Path path = Paths.get("src/main/resources/side_effects.txt");
            SideEffect sideEffect;
            try (BufferedReader reader = Files.newBufferedReader(path)) {

                String str;
                while ((str = reader.readLine()) != null) {
                    sideEffect = new SideEffect();
                    sideEffect.setText(str.replace(";", ""));
                    repository.save(sideEffect);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
