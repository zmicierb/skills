package com.barysevich.project.core.transport;


import com.barysevich.project.api.MailResult;
import com.barysevich.project.core.message.MailHeader;
import com.barysevich.project.core.message.MailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;


@Component
public class SmtpMailTransport implements MailTransport
{
    private static final Logger logger = LoggerFactory.getLogger(SmtpMailTransport.class);

    private final JavaMailSender mailSender;


    @Autowired
    public SmtpMailTransport(final JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }


    @Override
    public MailResult sendEmail(final MailMessage message)
    {
        final MimeMessagePreparator preparedMessage = prepare(message);

        try
        {
            logger.trace("Sending email");

            mailSender.send(preparedMessage);

            logger.debug("Email was sent successfully");

            return MailResult.success();
        } catch (final Exception e)
        {
            logger.warn("Could not send email", e);

            return MailResult.failed();
        }
    }


    private MimeMessagePreparator prepare(final MailMessage message)
    {
        return mimeMessage -> {
            final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

            final MailHeader header = message.getHeader();

            messageHelper.setTo(header.getToAddress());
            messageHelper.setFrom(header.getFromAddress());
            messageHelper.setSubject(header.getSubject());

            messageHelper.setText(message.getHtmlText(), true);
        };
    }
}
