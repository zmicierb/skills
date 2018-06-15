package com.barysevich.project.core.template.mail;


import com.barysevich.project.core.message.MailHeader;
import com.barysevich.project.localization.Locale;
import com.google.common.base.Preconditions;


public class RegistrationTemplate
{
    private static final String TEMPLATE_NAME = "register";

    private static final String SUBJECT = "Registration";

    private final String fromAddress;

    private final String toAddress;

    private final Locale locale;


    private RegistrationTemplate(final String fromAddress, final String toAddress, final Locale locale)
    {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.locale = locale;
    }


    public static Builder builder()
    {
        return new Builder();
    }


    public static class Builder
    {
        private String fromAddress;

        private String toAddress;

        private Locale locale;


        private Builder()
        {
        }


        public Builder withFromAddress(final String fromAddress)
        {
            this.fromAddress = fromAddress;
            return this;
        }


        public Builder withToAddress(final String toAddress)
        {
            this.toAddress = toAddress;
            return this;
        }


        public Builder withLocale(final Locale locale)
        {
            this.locale = locale;
            return this;
        }


        public RegistrationTemplate build()
        {
            return new RegistrationTemplate(fromAddress, toAddress, locale);
        }

    }


    public MailTemplate done()
    {
        Preconditions.checkArgument(fromAddress != null, "Source address is mandatory");
        Preconditions.checkArgument(toAddress != null, "Destination address is mandatory");
        Preconditions.checkArgument(locale != null, "Locale is mandatory");

        final MailHeader header = new MailHeader(fromAddress, toAddress, SUBJECT);

        return new MailTemplate(header, TEMPLATE_NAME, locale, null);
    }
}
