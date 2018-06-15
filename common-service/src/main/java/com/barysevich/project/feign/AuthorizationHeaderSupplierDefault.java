package com.barysevich.project.feign;


import com.barysevich.project.authorizationheader.AuthorizationHeaderSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuthorizationHeaderSupplierDefault implements AuthorizationHeaderSupplier
{
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationHeaderSupplierDefault.class);


    public String authorizationHeader()
    {
        logger.trace("Adding authorization header");

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication == null ? "LOH" : authentication.getCredentials().toString();
    }
}