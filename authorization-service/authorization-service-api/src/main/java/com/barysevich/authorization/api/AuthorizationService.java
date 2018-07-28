package com.barysevich.authorization.api;

import com.barysevich.authorization.api.request.LoginRequest;

public interface AuthorizationService {

    AuthorizationResult authenticate(final LoginRequest request);
}
