package com.barysevich.project.api.request;


import java.util.Objects;

import com.barysevich.project.localization.Locale;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static java.util.Objects.requireNonNull;


/**
 * POJO тело запроса для получения шаблона
 */
public class GetTemplateRequest
{
    /**
     * наименование шаблона
     */
    private final String templateName;

    /**
     * локаль
     */
    private final Locale locale;


    @JsonCreator
    public GetTemplateRequest(
        @JsonProperty("templateName") final String templateName,
        @JsonProperty("locale") final Locale locale)
    {
        this.templateName = requireNonNull(templateName);
        this.locale = locale;
    }


    @JsonProperty("templateName")
    public String getTemplateName()
    {
        return templateName;
    }


    @JsonProperty("locale")
    public Locale getLocale()
    {
        return locale;
    }


    @Override
    public String toString()
    {
        return "GetTemplateRequest{" +
            "templateName='" + templateName + '\'' +
            ", locale=" + locale +
            '}';
    }


    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        final GetTemplateRequest that = (GetTemplateRequest) o;

        if (!templateName.equals(that.templateName))
        {
            return false;
        }
        return locale.equals(that.locale);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(locale, templateName);
    }
}
