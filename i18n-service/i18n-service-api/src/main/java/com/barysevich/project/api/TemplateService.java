package com.barysevich.project.api;


import com.barysevich.project.api.common.GetTemplateError;
import com.barysevich.project.api.request.GetTemplateRequest;
import com.barysevich.project.result.Result;


public interface TemplateService
{
    /**
     * Метод получения шаблона в виде строки
     * @param getTemplateRequest тело запроса для получения шаблона - локаль, наименование шаблона
     * @return строку с содержанием шаблона, либюо код ошибки
     */
    Result<String, GetTemplateError> getTemplateByName(final GetTemplateRequest getTemplateRequest);
}
