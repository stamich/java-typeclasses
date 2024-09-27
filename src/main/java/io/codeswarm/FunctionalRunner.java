package io.codeswarm;

import io.codeswarm.patterns.applicative.Applicative;
import io.codeswarm.patterns.applicative.ApplicativeType;
import io.codeswarm.patterns.apply.Apply;
import io.codeswarm.patterns.apply.ApplyType;
import io.codeswarm.patterns.either.Either;
import io.codeswarm.patterns.either.EitherType;
import io.codeswarm.patterns.foldable.Foldable;
import io.codeswarm.patterns.foldable.FoldableType;
import io.codeswarm.patterns.functor.Functor;
import io.codeswarm.patterns.functor.FunctorType;
import io.codeswarm.patterns.monad.Monad;
import io.codeswarm.patterns.monad.MonadType;
import io.codeswarm.patterns.monoid.MonoidType;
import io.codeswarm.patterns.semigroup.SemigroupType;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalRunner {

    public static void main(String[] args) {

        semigroupCheck();
        monoidCheck();
        functorCheck();
        applyCheck();
        applicativeCheck();
        monadCheck();
        foldableCheck();
        eitherCheck();
    }

    private static void semigroupCheck() {
        SemigroupType<Integer> semigroupTypeInt = new SemigroupType<>(Integer::sum);
        SemigroupType<String> semigroupTypeString = new SemigroupType<>(String::concat);
        System.out.println("Semigroup check:");
        System.out.println(semigroupTypeInt.combine(5,4));
        System.out.println(semigroupTypeString.combine("Semigroup functional ", "type class"));
    }

    private static void monoidCheck() {
        MonoidType<Integer> monoidTypeInteger = new MonoidType<>(0, Integer::sum);
        MonoidType<String> monoidTypeString = new MonoidType<>("", String::concat);
        System.out.printf("%nMonoid check:%n");
        System.out.println(monoidTypeInteger.combine(2,3));
        System.out.println(monoidTypeInteger.empty());
        System.out.println(monoidTypeString.combine("Monoid ", "type class."));
        System.out.println(monoidTypeString.empty());
    }

    private static void functorCheck() {
        System.out.println();
        System.out.println("Functor check:");
        Functor<Integer> functorTypeIntegerMap = new FunctorType<>(7).map(i -> i + 1);
        Boolean functorTypeIntegerEq = new FunctorType<>(10).eq(7);
        Functor<String> functorTypeStringMap = new FunctorType<>("Java").map(s -> s.replace('a', 'e'));
        Boolean functorTypeStringEq = new FunctorType<>("Java").eq("Java");
        System.out.println(functorTypeIntegerMap);
        System.out.println(functorTypeIntegerEq);
        System.out.println(functorTypeStringMap);
        System.out.println(functorTypeStringEq);
    }

    private static void applyCheck() {
        System.out.println();
        System.out.println("Apply check:");
        Apply<Integer> apIntMap = new ApplyType<>(6).map(i -> i * 2);
        Apply<Integer> apAp = new ApplyType<>(2).ap(new ApplyType<>(i -> i + 1));
        System.out.println(apIntMap);;
        System.out.println(apAp);
    }

    private static void applicativeCheck() {
        System.out.println();
        System.out.println("Applicative check:");
        Applicative<Integer> applicativeIntMap = new ApplicativeType<>(5).map(i -> i * 3);
        System.out.println(applicativeIntMap);
        Applicative<Integer> applicativePure = new ApplicativeType<>(5).pure(2);
        System.out.println(applicativePure);
    }

    private static void monadCheck() {
        System.out.println();
        System.out.println("Monad check:");
        Monad<Integer> monadIntMap = new MonadType<>(7).map(i -> i + 10);
        System.out.println(monadIntMap);
        Monad<Integer> monadIntFlatMap = new MonadType<>(7).flatMap(x -> (y -> x * 3 + 2));
        System.out.println(monadIntFlatMap);
    }

    private static void foldableCheck() {
        System.out.println();
        System.out.println("Foldable check:");
        List<Integer> list = List.of(1,2,3,4,5);
        Foldable<List<Integer>, Integer> foldable = new FoldableType<>(list, 0);
        var resultLeft = foldable.foldLeft(List.of(list), 0, (acc, x) -> acc + x.get(acc));
        System.out.println(resultLeft);

//        var resultRight = foldable.foldRight(List.of(list), 0, (a, x) -> appendList(a, x));
//        var r = foldable.foldLeft(List.of(list), 0, Integer::sum);
//        System.out.println(foldable.foldLeft(list, 0, Integer::sum));
    }

    private static void eitherCheck() {
        System.out.println();
        System.out.println("Either check:");

        Optional<Integer> optional = Optional.of(1);
        Optional<Integer> nextOptional = Optional.of(2);
        Either<Integer, Integer> either = new EitherType<>(optional, nextOptional);
        System.out.println(either.left(1));
        System.out.println(either.right(2));
        System.out.println(Optional.ofNullable(either.map(x -> x + 1, y -> y + 2)));
        System.out.println(Optional.of(either.mapLeft(x -> x + 1)));
        System.out.println(Optional.of(either.mapRight(y -> y + 2)));
    }

    @SafeVarargs
    private static <T> List<T> appendList(List<T> list, T... elements) {
        return Stream.concat(list.stream(), Stream.of(elements))
                .collect(Collectors.toList());
    }

    private static <T> List<T> prependList(List<T> list, T element) {
        return Stream.concat(Stream.of(element), list.stream())
                .collect(Collectors.toList());
    }
}