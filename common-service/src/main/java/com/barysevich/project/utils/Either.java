package com.barysevich.project.utils;


import java.io.Serializable;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


/**
 * Сериализуемый класс, представляющий значение одного из двух возможных типов. Является альтернативой {@link Optional}
 * {@link Either#left} используется вместо {@link Optional#empty} и может содержать полезную информацию.
 * {@link Either#right} используется вместо {@link Optional#of} и используется аналогично.
 */
public abstract class Either<L, R> implements Serializable
{
    private static final long serialVersionUID = -5083557524966708477L;


    public static <L, R> Either<L, R> left(final L left)
    {
        requireNotNull(left);

        return new Left<>(left);
    }


    public static <L, R> Either<L, R> right(final R right)
    {
        requireNotNull(right);

        return new Right<>(right);
    }


    public abstract L left();


    public abstract R right();


    public abstract boolean isLeft();


    public abstract boolean isRight();


    public abstract <T> T fold(final Function<L, T> ifLeft, Function<R, T> ifRight);


    public abstract <T> Either<L, T> map(final Function<R, T> ifRight);


    public abstract <T> Either<L, T> flatMap(final Function<R, Either<L, T>> ifRight);


    public abstract Optional<R> toOption();


    public abstract R recover(final Function<L, R> ifLeft);


    public abstract R getOr(final Supplier<R> defaultValue);


    public abstract R getOr(final R defaultValue);


    public abstract void foreach(final Consumer<L> ifLeft, final Consumer<R> ifRight);


    private static void requireNotNull(final Object value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("Parameter is mandatory");
        }
    }


    private static final class Left<L, R> extends Either<L, R>
    {
        private final L value;


        private Left(final L value)
        {
            this.value = value;
        }


        @Override
        public L left()
        {
            return value;
        }


        @Override
        public R right()
        {
            throw new IllegalStateException("Right value is not present");
        }


        @Override
        public <T> T fold(final Function<L, T> ifLeft, final Function<R, T> ifRight)
        {
            return ifLeft.apply(value);
        }


        @Override
        public <T> Either<L, T> map(final Function<R, T> ifRight)
        {
            return new Left<>(value);
        }


        @Override
        public <T> Either<L, T> flatMap(final Function<R, Either<L, T>> ifRight)
        {
            return new Left<>(value);
        }


        @Override
        public Optional<R> toOption()
        {
            return Optional.empty();
        }


        @Override
        public R recover(final Function<L, R> ifLeft)
        {
            return ifLeft.apply(value);
        }


        @Override
        public R getOr(final Supplier<R> defaultValue)
        {
            return defaultValue.get();
        }


        @Override
        public R getOr(final R defaultValue)
        {
            return defaultValue;
        }


        @Override
        public void foreach(final Consumer<L> ifLeft, final Consumer<R> ifRight)
        {
            ifLeft.accept(value);
        }


        @Override
        public boolean isLeft()
        {
            return true;
        }


        @Override
        public boolean isRight()
        {
            return false;
        }


        @Override
        public String toString()
        {
            return "Either.Left(" + value + ")";
        }
    }

    private static final class Right<L, R> extends Either<L, R>
    {
        private final R value;


        private Right(final R value)
        {
            this.value = value;
        }


        @Override
        public L left()
        {
            throw new IllegalStateException("Left value is not present");
        }


        @Override
        public R right()
        {
            return value;
        }


        @Override
        public <T> T fold(final Function<L, T> ifLeft, final Function<R, T> ifRight)
        {
            return ifRight.apply(value);
        }


        @Override
        public <T> Either<L, T> map(final Function<R, T> ifRight)
        {
            return new Right<>(ifRight.apply(value));
        }


        @Override
        public <T> Either<L, T> flatMap(final Function<R, Either<L, T>> ifRight)
        {
            return ifRight.apply(value);
        }


        @Override
        public Optional<R> toOption()
        {
            return Optional.of(value);
        }


        @Override
        public R recover(final Function<L, R> ifLeft)
        {
            return value;
        }


        @Override
        public R getOr(final Supplier<R> defaultValue)
        {
            return value;
        }


        @Override
        public R getOr(final R defaultValue)
        {
            return value;
        }


        @Override
        public void foreach(final Consumer<L> ifLeft, final Consumer<R> ifRight)
        {
            ifRight.accept(value);
        }


        @Override
        public boolean isLeft()
        {
            return false;
        }


        @Override
        public boolean isRight()
        {
            return true;
        }


        @Override
        public String toString()
        {
            return "Either.Right(" + value + ")";
        }
    }
}
