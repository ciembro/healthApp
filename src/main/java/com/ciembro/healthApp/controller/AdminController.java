package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.facade.DrugFacade;
import com.ciembro.healthApp.service.EmotionalStateService;
import com.ciembro.healthApp.service.SideEffectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DrugFacade drugFacade;
    private final EmotionalStateService emotionService;
    private final SideEffectService sideEffectService;

    @PostMapping("/drugs")
    public void loadDrugsDataToDb(){
        drugFacade.updateDrugDatabase();
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
