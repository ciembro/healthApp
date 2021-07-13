package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.sideeffect.SideEffect;
import com.ciembro.healthApp.domain.sideeffect.SideEffectDto;
import com.ciembro.healthApp.domain.sideeffect.SideEffectToAddDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.SideEffectNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.mapper.SideEffectMapper;
import com.ciembro.healthApp.service.SideEffectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SideEffectController {

    private final SideEffectService sideEffectService;
    private final SideEffectMapper sideEffectMapper;

    @PostMapping(value = "/effects", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SideEffectDto addSideEffect(@RequestBody SideEffectToAddDto sideEffectDto) throws UserNotFoundException, DrugNotFoundException {
        SideEffect se = sideEffectMapper.mapToSideEffect(sideEffectDto);
        return sideEffectMapper.mapToSideEffectDto(sideEffectService.save(se));
    }

    @GetMapping("/effects/{drugId}")
    public List<SideEffectDto> getSideEffectsForDrug(Authentication authentication,
                                                     @PathVariable long drugId) throws UserNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<SideEffect> sideEffects = sideEffectService.getSideEffectsByDrugId(userDetails.getUsername(), drugId);
        return sideEffectMapper.mapToSideEffectDtoList(sideEffects);
    }

    @DeleteMapping(value = "/effects", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSideEffectForDrug(@RequestBody SideEffectDto sideEffectDto) throws UserNotFoundException, DrugNotFoundException, SideEffectNotFoundException {
        SideEffect sideEffect = sideEffectMapper.mapToSideEffect(sideEffectDto);
        sideEffectService.delete(sideEffect.getId());
    }

    @PutMapping(value = "/effects", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SideEffectDto updateSideEffect(@RequestBody SideEffectDto sideEffectDto)
            throws DrugNotFoundException, UserNotFoundException {

        SideEffect se = sideEffectMapper.mapToSideEffect(sideEffectDto);
        se = sideEffectService.save(se);
        return sideEffectMapper.mapToSideEffectDto(se);
    }

}
