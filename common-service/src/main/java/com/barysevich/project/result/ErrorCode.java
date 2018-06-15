package com.barysevich.project.result;


import com.fasterxml.jackson.annotation.JsonValue;


public interface ErrorCode
{
    @JsonValue
    String getCode();
}
