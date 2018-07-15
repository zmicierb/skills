package com.barysevich.project.email;


import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Обертка над Email'ом с минимальной валидацией.
 */
public class Email implements Serializable
{

    public static final String REGEXP = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*" +
        "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(REGEXP);

    private static final long serialVersionUID = 6929473916483212433L;

    private final String email;


    public Email(final String email)
    {
        if (!isValid(email))
        {
            throw new IllegalArgumentException("illegal email:'" + email + "'");
        }
        this.email = email.trim().toLowerCase();
    }


    public static boolean isValid(final String email)
    {
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }


    public static Optional<Email> parse(final String email)
    {
        if (isValid(email))
        {
            return Optional.of(new Email(email));
        }

        return Optional.empty();
    }


    @JsonValue
    public String asString()
    {
        return email;
    }


    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        final Email email = (Email) obj;
        return Objects.equals(this.email, email.email);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(email);
    }


    @Override
    public String toString()
    {
        return "Email{" +
            "email='" + email + '\'' +
            '}';
    }
}
