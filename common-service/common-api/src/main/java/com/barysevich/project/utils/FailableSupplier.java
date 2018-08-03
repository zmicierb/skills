package com.barysevich.project.utils;


import java.util.function.Supplier;


/**
 * Этот класс аналогичен {@link Supplier}, только метод get может кидать исключение.
 */
@FunctionalInterface
public interface FailableSupplier<T>
{
    T get() throws Exception;
}
