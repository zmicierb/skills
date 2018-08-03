package com.barysevich.project.utils;


import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


/**
 * {@code try} представляет собой объект, который может служить результатом выполнения функции. Этот объект будет
 * содержать либо результат успешного выполнения функции, либо {@link Exception} при неуспешном выполнении. Этот объект
 * похож на {@link Either}, но семантически он другой, так как создаётся в других обстоятельствах.
 *
 * Этот метод не реализует методы recoverWith и recover, так как в случае try..catch есть pattern matching, который
 * отсутствует в джаве.
 */
public abstract class Try<T>
{
    private Try()
    {
    }


    /**
     * Возвращает {@code true}, исполнение кода завершилось с {@link Exception}
     */
    public abstract boolean isFailure();


    /**
     * Возвращает {@code true}, исполнение кода завершилось успешно
     */
    public abstract boolean isSuccess();


    /**
     * Возвращает значение успешного выполнения выражения, либо {@code defaultValue}
     */
    public abstract T orElse(T defaultValue);


    /**
     * Возвращает значение успешного выполнения выражения, либо {@code defaultValue} созданный лейзи
     */
    public abstract T getOrElse(Supplier<? extends T> defaultValueSupplier);


    /**
     * Возвращает значение успешного выполнения выражения, либо {@code defaultValue} созданный лейзи
     */
    public abstract T getOrElse(final Function<Exception, ? extends T> function);


    /**
     * Возвращает значение успешного выполнения выражения, либо кидает {@link Exception}
     */
    public abstract T get() throws Exception;


    /**
     * Применяет функцию {@code function} к значению, если было успешное выполнение, либо возвращает {@code Try.Failure}
     * с хранящимся {@link Exception}.
     */
    public abstract <R> Try<R> map(final Function<? super T, R> function);


    /**
     * Применяет функцию {@code function} к значению, если было успешное выполнение и разворачивает {@link Try}, либо
     * возвращает {@code Try.Failure} с хранящимся {@link Exception}.
     */
    public abstract <R> Try<R> flatMap(final Function<? super T, Try<R>> function);


    /**
     * Превращает {@link Try} в {@link Either}.
     */
    public abstract Either<Exception, T> toEither();


    /**
     * Превращает {@link Try} в {@link Optional}
     */
    public abstract Optional<T> toOption(final Consumer<Exception> exceptionConsumer);


    /**
     * Превращает {@link Try} в {@link Optional}.
     * Ахтунг! Все исключения, выброшенные внутри, останутся внутри. Рекомендуется использовать
     * {@link Try#toOption(Consumer)}.
     */
    public abstract Optional<T> toOption();


    /**
     * Создаёт новый экземпляр {@link Try}, выполнив код, переданный в {@code failableSupplier}
     */
    public static <T> Try<T> apply(final FailableSupplier<T> failableSupplier)
    {
        try
        {
            return new Success<>(failableSupplier.get());
        }
        catch (final Exception ex)
        {
            return new Failure<>(ex);
        }
    }


    private static final class Success<K> extends Try<K>
    {
        private final K value;


        private Success(final K value)
        {
            this.value = value;
        }


        @Override
        public boolean isFailure()
        {
            return false;
        }


        @Override
        public boolean isSuccess()
        {
            return true;
        }


        @Override
        public K getOrElse(final Supplier<? extends K> defaultValueSupplier)
        {
            return value;
        }


        @Override
        public K getOrElse(final Function<Exception, ? extends K> function)
        {
            return value;
        }


        @Override
        public K orElse(final K defaultValue)
        {
            return value;
        }


        @Override
        public K get() throws Exception
        {
            return value;
        }


        @Override
        public <R> Try<R> map(final Function<? super K, R> function)
        {
            return new Success<>(function.apply(value));
        }


        @Override
        public <R> Try<R> flatMap(final Function<? super K, Try<R>> function)
        {
            return function.apply(value);
        }


        @Override
        public Either<Exception, K> toEither()
        {
            return Either.right(value);
        }


        @Override
        public Optional<K> toOption(final Consumer<Exception> exceptionConsumer)
        {
            return toOption();
        }

        @Override
        public Optional<K> toOption()
        {
            return Optional.of(value);
        }


        @Override
        public String toString()
        {
            return "Success(" + value + ")";
        }
    }


    private static class Failure<K> extends Try<K>
    {
        private final Exception exception;


        private Failure(final Exception exception)
        {
            this.exception = exception;
        }


        @Override
        public boolean isFailure()
        {
            return true;
        }


        @Override
        public boolean isSuccess()
        {
            return false;
        }


        @Override
        public K getOrElse(final Supplier<? extends K> defaultValueSupplier)
        {
            return defaultValueSupplier.get();
        }


        @Override
        public K getOrElse(final Function<Exception, ? extends K> function)
        {
            return function.apply(exception);
        }


        @Override
        public K orElse(final K defaultValue)
        {
            return defaultValue;
        }


        @Override
        public K get() throws Exception
        {
            throw exception;
        }


        @Override
        public <R> Try<R> map(final Function<? super K, R> function)
        {
            return new Failure<>(exception);
        }


        @Override
        public <R> Try<R> flatMap(final Function<? super K, Try<R>> function)
        {
            return new Failure<>(exception);
        }


        @Override
        public Either<Exception, K> toEither()
        {
            return Either.left(exception);
        }


        @Override
        public Optional<K> toOption(final Consumer<Exception> exceptionConsumer)
        {
            exceptionConsumer.accept(exception);

            return toOption();
        }

        @Override
        public Optional<K> toOption()
        {
            return Optional.empty();
        }


        @Override
        public String toString()
        {
            return "Failure(" + exception + ")";
        }
    }
}
