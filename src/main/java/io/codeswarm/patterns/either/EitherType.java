package io.codeswarm.patterns.either;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public final class EitherType<L,R> implements Either<L,R> {

    final Optional<L> left;
    final Optional<R> right;

    public EitherType(Optional<L> l, Optional<R> r) {
        left=l;
        right=r;
    }

    public Either<L,R> left(L value) {
        return new EitherType<>(Optional.of(value), Optional.empty());
    }

    public Either<L,R> right(R value) {
        return new EitherType<>(Optional.empty(), Optional.of(value));
    }

    public <T> T map(Function<? super L, ? extends T> lFunc, Function<? super R, ? extends T> rFunc){
        return left.<T>map(lFunc).orElseGet(()->right.map(rFunc).get());
    }

    public <T> Either<T,R> mapLeft(Function<? super L, ? extends T> lFunc){
        return new EitherType<>(left.map(lFunc),right);
    }

    public <T> Either<L,T> mapRight(Function<? super R, ? extends T> rFunc){
        return new EitherType<>(left, right.map(rFunc));
    }

    public void apply(Consumer<? super L> lFunc, Consumer<? super R> rFunc){
        left.ifPresent(lFunc);
        right.ifPresent(rFunc);
    }

    public String toString() {
        return "EitherType{left = " + left + ", right = " + right + "}";
    }
}
