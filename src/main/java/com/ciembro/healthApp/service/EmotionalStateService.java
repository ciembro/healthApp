package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.EmotionalState;
import com.ciembro.healthApp.repository.EmotionalStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class EmotionalStateService {

    @Autowired
    private EmotionalStateRepository repository;

    public void loadEmotionalStates(){

        Path path = Paths.get("src/main/resources/emotions");
        EmotionalState emotionalState;
        try (BufferedReader reader = Files.newBufferedReader(path)) {

            String str;
            while ((str = reader.readLine()) != null) {
                emotionalState = new EmotionalState();
                String[] line = str.split(";");
                emotionalState.setEngText(line[0]);
                emotionalState.setPlText(line[1]);
                repository.save(emotionalState);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
