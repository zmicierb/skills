//package com.barysevich.project.api;
//
//
//import com.barysevich.project.api.common.SendMailError;
//import com.barysevich.project.api.model.MailNotificationMessage;
//import com.barysevich.project.result.Result;
//import feign.RequestLine;
//
//
//public interface MailServiceFeign extends MailService
//{
//    @Override
//    @RequestLine("GET /mail/send")
//    Result<Void, SendMailError> sendMail(final MailNotificationMessage mailNotificationMessage);
//}
