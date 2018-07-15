package com.barysevich.project.result;


public class Result<T, ErrorCodeT extends ErrorCode>
{
    private static final int SUCCESS = 0;

    private static final int FAILED = 1;

    private int status;

    private T result;

    private ErrorCodeT errorCode;


    @SuppressWarnings("unused")
    Result()
    {
    }


    private Result(final int status, final T result, final ErrorCodeT errorCode)
    {
        this.status = status;
        this.result = result;
        this.errorCode = errorCode;
    }


    public static <T, ErrorCodeT extends ErrorCode> Result<T, ErrorCodeT> success(final T result)
    {
        return new Result<>(SUCCESS, result, null);
    }


    public static <T, ErrorCodeT extends ErrorCode> Result<T, ErrorCodeT> success()
    {
        return new Result<>(SUCCESS, null, null);
    }


    public static <T, ErrorCodeT extends ErrorCode> Result<T, ErrorCodeT> failed(final ErrorCodeT code)
    {
        return new Result<>(FAILED, null, code);
    }


    public static <T, ErrorCodeT extends ErrorCode> Result<T, ErrorCodeT> failed(final T result, final ErrorCodeT code)
    {
        return new Result<>(FAILED, result, code);
    }


    public boolean isSuccess()
    {
        return SUCCESS == status;
    }


    public T getResult()
    {
        return result;
    }


    public int getStatus()
    {
        return status;
    }


    public ErrorCodeT getErrorCode()
    {
        return errorCode;
    }


    @Override
    public String toString()
    {
        return "Result{" +
            "status=" + status +
            ", result=" + result +
            ", errorCode=" + errorCode +
            '}';
    }
}
