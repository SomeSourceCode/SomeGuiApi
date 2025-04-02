/*
 * Copyright 2025, SomeSourceCode - MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the “Software”), to deal in
 * the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.somesourcecode.someguiapi.state;

import java.util.function.Function;

/**
 * The basis for all read-only states.
 * This provides the basic functionality for all read-only states,
 * including common state transformations.
 *
 * <h3>Transformations</h3>
 *
 * Transformations are used to convert the value of a state into another type.
 * The resulting state is read-only and cannot be modified. These transformations
 * are typically used to reflect a modification of the original state.
 *
 * @param <T> the type of the state's value
 * @since 3.0.0
 */
public interface ReadOnlyState<T> extends ObservableValue<T> {

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * is equal to the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isEqualTo(T value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * is equal to the value of the given {@link ObservableValue}, {@code false} otherwise.
	 *
	 * @param value the observable to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isEqualTo(ObservableValue<T> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * is not equal to the given value, {@code false} otherwise.
	 * This is preferred over {@code isEqualTo(value).negate()}, as it does not
	 * introduce an intermediate state.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isNotEqualTo(T value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
     * is not equal to the value of the given {@link ObservableValue}, {@code false} otherwise.
	 * This is preferred over {@code isEqualTo(value).negate()}, as it does not
	 * introduce an intermediate state.
	 *
	 * @param value the observable to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isNotEqualTo(ObservableValue<T> value);

	/**
	 * Creates a new {@link ReadOnlyStringState} that is a string representation of the value
	 * of this state, typically according to the {@link String#valueOf(Object)} method.
	 *
	 * @return the new {@code ReadOnlyStringState}
	 * @since 3.0.0
	 */
	ReadOnlyStringState asString();

	/**
	 * Creates a new {@link ReadOnlyBooleanState} according to the given mapper.
	 *
	 * @param mapper the function to map the value of this state to a boolean
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState mapToBoolean(Function<T, Boolean> mapper);

	/**
	 * Creates a new {@link ReadOnlyIntegerState} according to the given mapper.
	 *
	 * @param mapper the function to map the value of this state to an integer
	 * @return the new {@code ReadOnlyIntegerState}
	 * @since 3.0.0
	 */
	ReadOnlyIntegerState mapToInteger(Function<T, Integer> mapper);

	/**
	 * Creates a new {@link ReadOnlyLongState} according to the given mapper.
	 *
	 * @param mapper the function to map the value of this state to a long
	 * @return the new {@code ReadOnlyLongState}
	 * @since 3.0.0
	 */
	ReadOnlyLongState mapToLong(Function<T, Long> mapper);

	/**
	 * Creates a new {@link ReadOnlyFloatState} according to the given mapper.
	 *
	 * @param mapper the function to map the value of this state to a float
	 * @return the new {@code ReadOnlyFloatState}
	 * @since 3.0.0
	 */
	ReadOnlyFloatState mapToFloat(Function<T, Float> mapper);

	/**
	 * Creates a new {@link ReadOnlyDoubleState} according to the given mapper.
	 *
	 * @param mapper the function to map the value of this state to a double
	 * @return the new {@code ReadOnlyDoubleState}
	 * @since 3.0.0
	 */
	ReadOnlyDoubleState mapToDouble(Function<T, Double> mapper);

	/**
	 * Creates a new {@link ReadOnlyStringState} according to the given mapper.
	 *
	 * @param mapper the function to map the value of this state to a string
	 * @return the new {@code ReadOnlyStringState}
	 * @since 3.0.0
	 */
	ReadOnlyStringState mapToString(Function<T, String> mapper);

	/**
	 * Creates a new {@link ReadOnlyObjectState} according to the given mapper.
	 *
	 * @param <E> the type of the new state
	 * @param mapper the function to map the value of this state to an object
	 * @return the new {@code ReadOnlyObjectState}
	 * @since 3.0.0
	 */
	<E> ReadOnlyObjectState<E> mapToObject(Function<T, E> mapper);

}
