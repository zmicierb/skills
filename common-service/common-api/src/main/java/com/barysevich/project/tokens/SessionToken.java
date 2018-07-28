package com.barysevich.project.tokens;

import com.barysevich.project.utils.MaskUtils;
import com.barysevich.project.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonValue;

public class SessionToken {

    private final String token;

    private static final int TOKEN_SIZE = 64;


    public SessionToken(final String token)
    {
        if (!isValid(token))
        {
            throw new IllegalArgumentException("illegal session token:'" + token + "'");
        }
        this.token = token;
    }


    public static boolean isValid(final String token)
    {
        return StringUtils.hasText(token) && token.length() == TOKEN_SIZE;
    }

    @JsonValue
    public String asString()
    {
        return token;
    }


    @Override
    public String toString()
    {
        return MaskUtils.mask(token);
    }
}
