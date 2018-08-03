package com.barysevich.authorization.core;


import com.barysevich.authorization.api.AuthorizationResult;
import com.barysevich.authorization.api.AuthorizationService;
import com.barysevich.authorization.api.request.LoginRequest;
import com.barysevich.authorization.core.dao.RegistrationDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationServiceImpl implements AuthorizationService
{
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

    private final RegistrationDAO dao;


    @Autowired
    public AuthorizationServiceImpl(final RegistrationDAO dao)
    {
        this.dao = dao;
    }


    @Override
    public Long reserveId()
    {
        final Long reservedId = dao.reserveId();

        logger.debug("reserved id={}", reservedId);

        return reservedId;
    }


    @Override
    public AuthorizationResult authenticate(final LoginRequest request)
    {
        return null;
    }
}
