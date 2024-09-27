package io.codeswarm.patterns.either;

public interface Either<L,R> {

        Either<L,R> left(L value);
        Either<L,R> right(R value);
        <T> T map(java.util.function.Function<? super L, ? extends T> lFunc,
                java.util.function.Function<? super R, ? extends T> rFunc);
        <T> Either<T,R> mapLeft(java.util.function.Function<? super L, ? extends T> lFunc);
        <T> Either<L,T> mapRight(java.util.function.Function<? super R, ? extends T> rFunc);
        void apply(java.util.function.Consumer<? super L> lFunc,
                java.util.function.Consumer<? super R> rFunc);
}
