package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.SideEffect;
import com.ciembro.healthApp.repository.SideEffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SideEffectService {

    @Autowired
    SideEffectRepository repository;

    public SideEffect save(SideEffect sideEffect){
        return repository.save(sideEffect);
    }

    public void loadSideEffects(){

        String allSideEffects = "ból głowy;\n" +
                "senność;\n" +
                "bezsenność;\n" +
                "problemy z pamięcią;\n" +
                "nadmiar energii;\n" +
                "nasilenie stanów lękowych;\n" +
                "uczucie niepokoju;\n" +
                "suchość w ustach;\n" +
                "problemy z koncentracją;\n" +
                "nudności;\n" +
                "niepokój psychoruchowy;\n" +
                "nietypowe sny;\n" +
                "trudności w zasypianiu;\n" +
                "zawroty głowy;\n" +
                "omdlenia;\n" +
                "ziewanie;\n" +
                "drżenia;\n" +
                "biegunka;\n" +
                "zaparcia;\n" +
                "wymioty;\n" +
                "zaburzenia seksualne;\n" +
                "zwiększenie masy ciała;\n" +
                "kołatanie serca;\n" +
                "zmniejszenie masy ciała;\n" +
                "zmęczenie;\n" +
                "podwójne widzenie;\n" +
                "niewyraźne widzenie;\n" +
                "kaszel;\n" +
                "duszności;\n" +
                "nieprzyjemny smak w ustach;\n" +
                "zmniejszenie apetytu;";

        List<String> splitedEffects = Arrays.asList(allSideEffects.split(";\n"));
        List<SideEffect> sideEffects = splitedEffects.stream()
                .map(s -> {
                    SideEffect sideEffect = new SideEffect();
                    sideEffect.setText(s);
                    return sideEffect;
                })
                .collect(Collectors.toList());

        for (SideEffect sideEffect : sideEffects){
            save(sideEffect);
        }

    }


}
