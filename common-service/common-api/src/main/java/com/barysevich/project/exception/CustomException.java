package com.barysevich.project.exception;


public class CustomException extends RuntimeException
{
    private static final long serialVersionUID = -7591246163880227214L;

    private final CustomExceptionInfo exceptionInfo;


    public CustomException(final String errorCode, final String errorDescription)
    {
        this.exceptionInfo = new CustomExceptionInfo(errorCode, errorDescription);
    }


    public CustomException(final CustomExceptionInfo exceptionInfo)
    {
        this.exceptionInfo = exceptionInfo;
    }


    public CustomExceptionInfo getExceptionInfo()
    {
        return exceptionInfo;
    }


    public boolean matchesTo(final String errorCode)
    {
        return exceptionInfo.getCode().equals(errorCode);
    }


    @Override
    public String getMessage()
    {
        return exceptionInfo.getDescription();
    }


    @Override
    public String toString()
    {
        return "CustomException{" + "exceptionInfo=" + exceptionInfo + '}';
    }
}
