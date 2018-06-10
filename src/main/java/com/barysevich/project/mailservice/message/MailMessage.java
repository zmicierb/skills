package com.barysevich.project.mailservice.message;

public class MailMessage {
    private final MailHeader header;

    private final String htmlText;


    public MailMessage(final MailHeader header, final String htmlText)
    {
        this.header = header;
        this.htmlText = htmlText;
    }


    public MailHeader getHeader()
    {
        return header;
    }


    public String getHtmlText()
    {
        return htmlText;
    }
}
