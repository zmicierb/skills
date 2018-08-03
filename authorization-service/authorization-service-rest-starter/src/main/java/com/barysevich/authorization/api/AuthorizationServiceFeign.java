package com.barysevich.authorization.api;

import com.barysevich.authorization.api.request.LoginRequest;
import feign.RequestLine;

public interface AuthorizationServiceFeign extends AuthorizationService {

    @Override
    @RequestLine("POST /registration/initiate")
    Long reserveId();

    @Override
    @RequestLine("POST /auth?info={info}")
    AuthorizationResult authenticate(final LoginRequest info);
}
