package com.barysevich.project.exception;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Прокидывается JSON-ом на неуспешных ответах и собирается в {@link CustomException}
 */
public class CustomExceptionInfo implements Serializable
{
    private static final long serialVersionUID = 2305670883455999098L;

    private String code;

    private String description;

    private final List<String> trace = new ArrayList<>();


    public static CustomExceptionInfo createDefault(final String description)
    {
        return new CustomExceptionInfo(CustomExceptionCodes.DEFAULT_CODE, description);
    }


    @SuppressWarnings("unused")
    CustomExceptionInfo()
    {
    }


    CustomExceptionInfo(final String code, final String description)
    {
        this.code = code;
        this.description = description;
    }


    public String getCode()
    {
        return code;
    }


    public String getDescription()
    {
        return description;
    }


    public List<String> getTrace()
    {
        return trace;
    }


    public void appendTrace(final String traceLog)
    {
        trace.add(traceLog);
    }


    @Override
    public String toString()
    {
        return "CustomExceptionInfo{" + "code='" + code + '\'' + ", description='" + description + '\''
            + ", trace= \n[" + String.join(";\n", trace) + "]}";
    }
}
