package com.barysevich.project.feign;


import java.io.IOException;

import com.barysevich.project.exception.CustomException;
import com.barysevich.project.exception.CustomExceptionInfo;
import com.barysevich.project.utils.SerializationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static feign.FeignException.errorStatus;


/**
 * Декодирует HTTP-ответы с ошибками в java-исключения
 */
public class CustomErrorDecoder implements ErrorDecoder
{
    private static final Logger logger = LoggerFactory.getLogger(CustomErrorDecoder.class);

    private static final ObjectMapper MAPPER = SerializationUtils.MAPPER;


    @Override
    public Exception decode(final String methodKey, final Response response)
    {
        if (response.status() == 451) // UNAVAILABLE_FOR_LEGAL_REASONS - кидаем по контракту
        {
            return customException(methodKey, response);
        }
        else if (response.status() >= 400 && response.status() <= 499)
        {
            logger.warn("[{}] An exception occurred: {}", response.status(), response.reason());
            final CustomExceptionInfo defaultInfo = CustomExceptionInfo.createDefault(response.reason());
            defaultInfo.appendTrace(methodKey);
            return new CustomException(defaultInfo);
        }
        else if (response.status() >= 500 && response.status() <= 599) // возможно 500+ обрабатывать иначе
        {
            logger.warn("[{}] An exception occurred: {}", response.status(), response.reason());
            final CustomExceptionInfo defaultInfo = CustomExceptionInfo.createDefault(response.reason());
            defaultInfo.appendTrace(methodKey);
            return new CustomException(defaultInfo);
        }

        return errorStatus(methodKey, response);
    }


    /**
     * Собирает CustomException из JSON ответа. Если при распознавании исключения из ответа вылетело исключение, то
     * формируем новое дефолтное и выбрасываем его. Таким образом, сможем найти концы и нормально обработать исключение
     * на уровнях выше.
     *
     * @param methodKey интерфейс сервиса, сигнатура вызванного метода
     * @param response HTTP-ответ сервиса
     */
    private CustomException customException(final String methodKey, final Response response)
    {
        try
        {
            final CustomExceptionInfo parsedInfo;
            final String exceptionBody = Util.toString(response.body().asReader());
            logger.warn("An CustomException as a raw string: {}", exceptionBody);
            parsedInfo = MAPPER.readValue(exceptionBody, CustomExceptionInfo.class);
            parsedInfo.appendTrace(methodKey);
            return new CustomException(parsedInfo);
        }
        catch (final IOException ioe)
        {
            logger.warn("An exception occurred while parsing http response", ioe);
            final CustomExceptionInfo defaultInfo = CustomExceptionInfo.createDefault(ioe.getMessage());
            defaultInfo.appendTrace(methodKey);
            return new CustomException(defaultInfo);
        }
    }
}