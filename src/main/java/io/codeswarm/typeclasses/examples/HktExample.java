package io.codeswarm.typeclasses.examples;

import io.codeswarm.typeclasses.data.Either;
import io.codeswarm.typeclasses.data.EitherK;
import io.codeswarm.typeclasses.data.EitherKinds;
import io.codeswarm.typeclasses.data.Option;
import io.codeswarm.typeclasses.data.OptionK;
import io.codeswarm.typeclasses.data.OptionKinds;
import io.codeswarm.typeclasses.data.Validated;
import io.codeswarm.typeclasses.data.ValidatedK;
import io.codeswarm.typeclasses.data.ValidatedKinds;
import io.codeswarm.typeclasses.hkt.Kind;

/**
 * Demonstrates widening concrete ADTs to the higher-kinded encoding and
 * narrowing them back without changing their runtime values.
 */
public final class HktExample {

    private HktExample() {
    }

    /**
     * Runs the higher-kinded encoding example.
     *
     * @param args ignored command-line arguments
     */
    public static void main(final String[] args) {
        final Option<Integer> option = Option.some(42);
        final Kind<OptionK, Integer> optionKind = OptionKinds.widen(option);
        System.out.println(OptionKinds.narrow(optionKind));

        final Either<String, Integer> either = Either.right(42);
        final Kind<EitherK<String>, Integer> eitherKind = EitherKinds.widen(either);
        System.out.println(EitherKinds.narrow(eitherKind));

        final Validated<String, Integer> validated = Validated.valid(42);
        final Kind<ValidatedK<String>, Integer> validatedKind = ValidatedKinds.widen(validated);
        System.out.println(ValidatedKinds.narrow(validatedKind));
    }
}
