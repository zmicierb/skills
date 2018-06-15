package com.barysevich.project.api.common;


import com.barysevich.project.result.ErrorCode;


/**
 * Ошибки получения шаблонов
 */
public enum GetTemplateError implements ErrorCode
{
    LOCALE_NOT_FOUND("LOCALE_NOT_FOUND"),
    TEMPLATE_NOT_FOUND("TEMPLATE_NOT_FOUND"),;

    private final String code;


    GetTemplateError(final String code)
    {
        this.code = code;
    }


    @Override
    public String getCode()
    {
        return code;
    }
}