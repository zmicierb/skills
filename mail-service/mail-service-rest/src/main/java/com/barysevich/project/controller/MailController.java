//package com.barysevich.project.controller;
//
//
//import javax.validation.Valid;
//
//import com.barysevich.project.api.MailService;
//import com.barysevich.project.api.common.SendMailError;
//import com.barysevich.project.api.model.MailNotificationMessage;
//import com.barysevich.project.result.Result;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping(value = "/mail/send", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//public class MailController
//{
//    private final MailService mailService;
//
//
//    public MailController(final MailService mailService)
//    {
//        this.mailService = mailService;
//    }
//
//
//    @GetMapping
//    @ApiOperation(value = "Send mail")
//    public Result<Void, SendMailError> send(
//        @RequestBody @Valid final MailNotificationMessage mailNotificationMessage)
//    {
//        return mailService.sendMail(mailNotificationMessage);
//    }
//}
