package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.domain.sideeffect.SideEffect;
import com.ciembro.healthApp.domain.sideeffect.SideEffectDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.mapper.SideEffectMapper;
import com.ciembro.healthApp.security.domain.JwtUtil;
import com.ciembro.healthApp.service.SideEffectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SideEffectController {

    private final JwtUtil jwtUtil;
    private final SideEffectService sideEffectService;
    private final SideEffectMapper sideEffectMapper;
    private final DrugMapper drugMapper;



    @PutMapping("/effects")
    public SideEffectDto updateSideEffect(Authentication authentication,
                                       @RequestHeader(name="Authorization") String token,
                                       @RequestBody SideEffectDto sideEffectDto) throws DrugNotFoundException, UserNotFoundException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (jwtUtil.validateToken(token, userDetails)){
            SideEffect se = sideEffectMapper.mapToSideEffect(sideEffectDto);
            se = sideEffectService.save(se);
            return sideEffectMapper.mapToSideEffectDto(se);
        }
        return null;
    }

}
