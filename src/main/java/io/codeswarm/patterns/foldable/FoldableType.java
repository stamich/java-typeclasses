package io.codeswarm.patterns.foldable;

import java.util.*;
import java.util.function.BiFunction;

public class FoldableType<T,U> implements Foldable<T,U> {

    public final T firstValue;
    public final U secondValue;

    public FoldableType(T firstValue, U secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public static <T> T head(List<T> list) {
        return !list.isEmpty()
                ? list.get(0)
                : null;
    }

    public static <T> List<T> tail(List<T> list) {
        List<T> workList = copy(list);
        workList.remove(0);
        return !list.isEmpty()
                ? Collections.unmodifiableList(workList)
                : null;
    }

    private static <T> List<T> copy(List<T> list) {
        return new ArrayList<>(list);
    }

//    @Override
//    public U foldLeft(List<T> list, U initialValue, BiFunction<U, T, U> accumulator) {
//        U result = initialValue;
//        for (T element: list) {
//            result = accumulator.apply(result, element);
//        }
//        return result;
//    }

    @Override
    public U foldLeft(List<T> list, U initialValue, BiFunction<U, T, U> accumulator) {
        return list.stream()
                .reduce(initialValue, accumulator, (a, b) -> b);
    }

    @Override
    public U foldRight(List<T> list, U initialValue, BiFunction<T, U, U> accumulator) {
        return list.isEmpty()
                ? initialValue
                : accumulator.apply(head(list), foldRight(Objects.requireNonNull(tail(list)), initialValue, accumulator));
    }
}
