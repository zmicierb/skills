package com.barysevich.project.mailservice.template.mail;

import com.barysevich.project.mailservice.message.MailHeader;

import java.util.Map;

public class MailTemplate {
    private final MailHeader header;

    private final String templateName;

    private final Locale locale;

    private final Map<String, Object> variables;


    public MailTemplate(final MailHeader header,
                        final String templateName,
                        final Locale locale,
                        final Map<String, Object> variables)
    {
        this.header = header;
        this.templateName = templateName;
        this.locale = locale;
        this.variables = variables;
    }


    public MailHeader getHeader()
    {
        return header;
    }


    public String getTemplateName()
    {
        return templateName;
    }


    public Locale getLocale()
    {
        return locale;
    }


    public Map<String, Object> getVariables()
    {
        return variables;
    }


    @Override
    public String toString()
    {
        return "MailTemplate{" +
                "header=" + header +
                ", templateName='" + templateName + '\'' +
                ", locale=" + locale +
                ", variables=" + variables +
                '}';
    }
}
