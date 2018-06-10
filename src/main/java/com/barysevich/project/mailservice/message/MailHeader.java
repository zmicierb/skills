package com.barysevich.project.mailservice.message;

public class MailHeader {
    private final String fromAddress;

    private final String toAddress;

    private final String subject;


    public MailHeader(final String fromAddress, final String toAddress, final String subject)
    {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.subject = subject;
    }


    public String getFromAddress()
    {
        return fromAddress;
    }


    public String getToAddress()
    {
        return toAddress;
    }


    public String getSubject()
    {
        return subject;
    }
}
