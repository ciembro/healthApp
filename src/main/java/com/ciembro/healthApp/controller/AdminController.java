package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.service.DrugApiService;
import com.ciembro.healthApp.service.EmotionalStateService;
import com.ciembro.healthApp.service.SideEffectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DrugApiService drugApiService;
    private final EmotionalStateService emotionService;

    private final SideEffectService sideEffectService;

    @PostMapping("/drugs")
    public void loadDrugsDataToDb(){
        drugApiService.updateDrugList();
    }

    @PostMapping("/emotions")
    public void loadEmotionalStatesToDb(){
        emotionService.insertEmotionalStatesToDb();
    }

    @PostMapping("/effects")
    public void loadSideEffectsToDb(){
        sideEffectService.loadSideEffects();
    }
}
