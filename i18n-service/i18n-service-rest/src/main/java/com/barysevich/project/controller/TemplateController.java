package com.barysevich.project.controller;


import javax.validation.Valid;

import com.barysevich.project.api.TemplateService;
import com.barysevich.project.api.common.GetTemplateError;
import com.barysevich.project.api.request.GetTemplateRequest;
import com.barysevich.project.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/template", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TemplateController
{
    private final TemplateService templateService;


    public TemplateController(final TemplateService templateService)
    {
        this.templateService = templateService;
    }


    @GetMapping
    @ApiOperation(value = "Get template")
    public Result<String, GetTemplateError> getLocale(
        @RequestBody @Valid final GetTemplateRequest getTemplateRequest)
    {
        return templateService.getTemplateByName(getTemplateRequest);
    }
}
