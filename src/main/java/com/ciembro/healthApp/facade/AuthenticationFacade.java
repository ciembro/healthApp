package com.ciembro.healthApp.facade;

import com.ciembro.healthApp.domain.AuthenticationRequest;
import com.ciembro.healthApp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    @Autowired
    private AuthenticationService authenticationService;

    public ResponseEntity<?> createAuthenticationToken(AuthenticationRequest request)
            throws Exception {
        return authenticationService.createAuthenticationToken(request);
    }

}
