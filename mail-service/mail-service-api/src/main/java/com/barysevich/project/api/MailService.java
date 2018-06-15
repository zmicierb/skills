package com.barysevich.project.api;


import com.barysevich.project.api.common.SendMailError;
import com.barysevich.project.api.request.SendMailRequest;
import com.barysevich.project.result.Result;


/**
 * Send mail service
 */
public interface MailService
{
    Result<Void, SendMailError> sendMail(final SendMailRequest sendMailRequest);
}
