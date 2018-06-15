package com.barysevich.project.localization;


import com.barysevich.project.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.Optional;


public class Locale implements Serializable
{
    private static final int LENGTH = 2;

    private static final String DEFAULT = "EN";

    private static final long serialVersionUID = 4788377547333789038L;

    private final String name;


    public Locale()
    {
        this.name = DEFAULT;
    }


    public Locale(final String locale)
    {
        if (locale == null)
        {
            this.name = DEFAULT;
        } else if (!isValid(locale))
        {
            throw new IllegalArgumentException("Illegal locale format (" + LENGTH + " chars required)");
        } else
        {
            this.name = locale.toUpperCase();
        }
    }


    public static boolean isValid(final String locale)
    {
        return StringUtils.hasText(locale) && LENGTH == locale.length();
    }


    public static Optional<Locale> parse(final String locale)
    {
        if (isValid(locale))
        {
            return Optional.of(new Locale(locale));
        }

        return Optional.empty();
    }


    @JsonValue
    public String asString()
    {
        return name;
    }


    @Override
    public boolean equals(final Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Locale locale = (Locale) o;

        return name.equals(locale.name);
    }


    @Override
    public int hashCode()
    {
        return name.hashCode();
    }


    @Override
    public String toString()
    {
        return "Locale{" +
            "name='" + name + '\'' +
            '}';
    }
}
