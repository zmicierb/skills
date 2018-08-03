package com.barysevich.authorization.controller;

import com.barysevich.authorization.api.AuthorizationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthController
{
    private final AuthorizationService authorizationService;


    public AuthController(final AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }


    @PostMapping(value = "/registration/initiate")
    @ApiOperation(value = "Reserve id")
    public Long initiateRegistration() {
        return authorizationService.reserveId();
    }
}
