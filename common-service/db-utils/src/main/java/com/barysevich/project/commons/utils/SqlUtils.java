package com.barysevich.project.commons.utils;


import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.dao.EmptyResultDataAccessException;


/**
 * Утилиты маппинга sql exception'ов.
 */
public class SqlUtils
{
    public static <ResultT> Optional<ResultT> getOptional(Supplier<ResultT> supplier)
    {
        try
        {
            return Optional.ofNullable(supplier.get());
        }
        catch (EmptyResultDataAccessException ex)
        {
            return Optional.empty();
        }
    }


    public static <ResultT> ResultT getOrNull(Supplier<ResultT> supplier)
    {
        try
        {
            return supplier.get();
        }
        catch (EmptyResultDataAccessException ex)
        {
            return null;
        }
    }


    public static <ResultT> ResultT getFirstOrNull(Supplier<List<ResultT>> supplier)
    {
        try
        {
            return supplier.get().get(0);
        }
        catch (EmptyResultDataAccessException | IndexOutOfBoundsException ex)
        {
            return null;
        }
    }


    public static <ResultT> Optional<ResultT> getFirstOptional(Supplier<List<ResultT>> supplier)
    {
        try
        {
            return Optional.of(supplier.get().get(0));
        }
        catch (EmptyResultDataAccessException | IndexOutOfBoundsException ex)
        {
            return Optional.empty();
        }
    }
}
