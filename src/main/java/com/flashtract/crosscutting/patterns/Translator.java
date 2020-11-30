package com.flashtract.crosscutting.patterns;

/**
 * Translator design concept contract
 *
 * @author blanclabs
 * @version 1.0
 * @since 2019-01-09
 * @param <I> input parameter
 * @param <O> output parameter
 */

@FunctionalInterface
public interface Translator<I, O> {

    O translate(final I input);

}
