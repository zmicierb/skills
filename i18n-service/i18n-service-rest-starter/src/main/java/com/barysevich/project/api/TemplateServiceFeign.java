package com.barysevich.project.api;


import com.barysevich.project.api.common.GetTemplateError;
import com.barysevich.project.api.request.GetTemplateRequest;
import com.barysevich.project.result.Result;
import feign.RequestLine;


public interface TemplateServiceFeign extends TemplateService
{
    @Override
    @RequestLine("GET /template")
    Result<String, GetTemplateError> getTemplateByName(final GetTemplateRequest getTemplateRequest);
}