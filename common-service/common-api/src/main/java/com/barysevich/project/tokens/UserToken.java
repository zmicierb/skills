package com.barysevich.project.tokens;

import com.barysevich.project.utils.MaskUtils;
import com.barysevich.project.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonValue;

public class UserToken {

    private final String token;

    public UserToken(final String token)
    {
        if (!isValid(token))
        {
            throw new IllegalArgumentException("illegal user token:'" + token + "'");
        }
        this.token = token;
    }


    public static boolean isValid(final String token)
    {
        return StringUtils.hasText(token);
    }


    @JsonValue
    public String asString()
    {
        return token;
    }

    @Override
    public String toString() {
        return MaskUtils.maskAllBefore4LastChars(token);
    }
}
