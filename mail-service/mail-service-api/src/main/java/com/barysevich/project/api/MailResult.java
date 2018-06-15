package com.barysevich.project.api;


public class MailResult
{
    private boolean success;


    /**
     * Jackson requirement
     */
    @SuppressWarnings("unused")
    MailResult()
    {
    }


    private MailResult(final boolean success)
    {
        this.success = success;
    }


    public static MailResult success()
    {
        return new MailResult(true);
    }


    public static MailResult failed()
    {
        return new MailResult(false);
    }


    public boolean isSuccess()
    {
        return success;
    }


    @Override
    public String toString()
    {
        return "MailResult{" +
            "success=" + success +
            '}';
    }
}
