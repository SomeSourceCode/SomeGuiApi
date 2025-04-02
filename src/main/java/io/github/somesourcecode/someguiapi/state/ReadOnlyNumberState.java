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

/**
 * The basis for all read-only number states.
 * Those include {@link ReadOnlyIntegerState}, {@link ReadOnlyLongState}, {@link ReadOnlyFloatState},
 * and {@link ReadOnlyDoubleState}.
 *
 * @param <T> the number type of the state's value
 * @since 3.0.0
 */
public interface ReadOnlyNumberState<T extends Number> extends ReadOnlyState<T> {

	NumberState<T> asMutableState();

	/**
	 * Returns the current value as an int.
	 *
	 * @return the current value as an int
	 * @since 3.0.0
	 */
	int intValue();

	/**
	 * Returns the current value as a double.
	 *
	 * @return the current value as a double
	 * @since 3.0.0
	 */
	double doubleValue();

	/**
	 * Returns the current value as a long.
	 *
	 * @return the current value as a long
	 * @since 3.0.0
	 */
	long longValue();

	/**
	 * Returns the current value as a float.
	 *
	 * @return the current value as a float
	 * @since 3.0.0
	 */
	float floatValue();

	/**
	 * Returns this instance as a {@link ReadOnlyIntegerState}, if possible.
	 * If this state is not an integer state, it will return a new {@link ReadOnlyIntegerState} holding the
	 * current value as an integer according to {@link #intValue()}.
	 *
	 * @return this instance as a {@link ReadOnlyIntegerState}, or a new one
	 *         if this state is not an integer state
	 * @since 3.0.0
	 */
	ReadOnlyIntegerState asIntegerState();

	/**
	 * Returns this instance as a {@link ReadOnlyLongState}, if possible.
	 * If this state is not a long state, it will return a new {@link ReadOnlyLongState} holding the
	 * current value as a long according to {@link #longValue()}.
	 *
	 * @return this instance as a {@link ReadOnlyLongState}, or a new one
	 *         if this state is not a long state
	 * @since 3.0.0
	 */
	ReadOnlyLongState asLongState();

	/**
	 * Returns this instance as a {@link ReadOnlyFloatState}, if possible.
	 * If this state is not a float state, it will return a new {@link ReadOnlyFloatState} holding the
	 * current value as a float according to {@link #floatValue()}.
	 *
	 * @return this instance as a {@link ReadOnlyFloatState}, or a new one
	 *         if this state is not a float state
	 * @since 3.0.0
	 */
	ReadOnlyFloatState asFloatState();

	/**
	 * Returns this instance as a {@link ReadOnlyDoubleState}, if possible.
	 * If this state is not a double state, it will return a new {@link ReadOnlyDoubleState} holding the
	 * current value as a double according to {@link #doubleValue()}.
	 *
	 * @return this instance as a {@link ReadOnlyDoubleState}, or a new one
	 *         if this state is not a double state
	 * @since 3.0.0
	 */
	ReadOnlyDoubleState asDoubleState();

	/**
	 * Creates a new mutable {@link IntegerState} reflecting the current value of this state.
	 * If this state is not an integer state, the new state will hold the current value as an integer
	 * according to {@link #intValue()}.
	 *
	 * @return the new mutable state
	 * @since 3.0.0
	 */
	IntegerState asMutableIntegerState();

	/**
	 * Creates a new mutable {@link LongState} reflecting the current value of this state.
	 * If this state is not a long state, the new state will hold the current value as a long
	 * according to {@link #longValue()}.
	 *
	 * @return the new mutable state
	 * @since 3.0.0
	 */
	LongState asMutableLongState();

	/**
	 * Creates a new mutable {@link FloatState} reflecting the current value of this state.
	 * If this state is not a float state, the new state will hold the current value as a float
	 * according to {@link #floatValue()}.
	 *
	 * @return the new mutable state
	 * @since 3.0.0
	 */
	FloatState asMutableFloatState();

	/**
	 * Creates a new mutable {@link DoubleState} reflecting the current value of this state.
	 * If this state is not a double state, the new state will hold the current value as a double
	 * according to {@link #doubleValue()}.
	 *
	 * @return the new mutable state
	 * @since 3.0.0
	 */
	DoubleState asMutableDoubleState();

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is less than the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isLessThan(int value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is less than the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isLessThan(long value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is less than the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isLessThan(float value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is less than the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isLessThan(double value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is less than the value of the given {@link ObservableValue}, {@code false} otherwise.
	 *
	 * @param value the observable to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isLessThan(ObservableValue<? extends Number> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is less than or equal to the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isLessThanOrEqualTo(int value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is less than or equal to the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isLessThanOrEqualTo(long value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is less than or equal to the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isLessThanOrEqualTo(float value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is less than or equal to the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isLessThanOrEqualTo(double value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is less than or equal to the value of the given {@link ObservableValue}, {@code false} otherwise.
	 *
	 * @param value the observable to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isLessThanOrEqualTo(ObservableValue<? extends Number> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is greater than the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isGreaterThan(int value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is greater than the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isGreaterThan(long value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is greater than the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isGreaterThan(float value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is greater than the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isGreaterThan(double value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is greater than the value of the given {@link ObservableValue}, {@code false} otherwise.
	 *
	 * @param value the observable to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isGreaterThan(ObservableValue<? extends Number> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is greater than or equal to the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isGreaterThanOrEqualTo(int value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is greater than or equal to the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isGreaterThanOrEqualTo(long value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is greater than or equal to the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isGreaterThanOrEqualTo(float value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is greater than or equal to the given value, {@code false} otherwise.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isGreaterThanOrEqualTo(double value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is greater than or equal to the value of the given {@link ObservableValue}, {@code false} otherwise.
	 *
	 * @param value the observable to compare to
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isGreaterThanOrEqualTo(ObservableValue<? extends Number> value);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is between the given values, {@code false} otherwise.
	 * <p>
	 * This method is inclusive, meaning that the boundaries are included in the range.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyBooleanState}
	 * @see #isBetweenExclusive(int, int)
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isBetween(int min, int max);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is between the given values, {@code false} otherwise.
	 * <p>
	 * This method is inclusive, meaning that the boundaries are included in the range.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyBooleanState}
	 * @see #isBetweenExclusive(long, long)
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isBetween(long min, long max);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is between the given values, {@code false} otherwise.
	 * <p>
	 * This method is inclusive, meaning that the boundaries are included in the range.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyBooleanState}
	 * @see #isBetweenExclusive(float, float)
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isBetween(float min, float max);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is between the given values, {@code false} otherwise.
	 * <p>
	 * This method is inclusive, meaning that the boundaries are included in the range.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyBooleanState}
	 * @see #isBetweenExclusive(double, double)
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isBetween(double min, double max);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is between the given values, {@code false} otherwise.
	 * <p>
	 * This method is inclusive, meaning that the boundaries are included in the range.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyBooleanState}
	 */
	ReadOnlyBooleanState isBetween(ObservableValue<? extends Number> min, ObservableValue<? extends Number> max);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is between the given values, {@code false} otherwise.
	 * <p>
	 * This method is exclusive, meaning that the boundaries are not included in the range.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyBooleanState}
	 * @see #isBetween(int, int)
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isBetweenExclusive(int min, int max);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is between the given values, {@code false} otherwise.
	 * <p>
	 * This method is exclusive, meaning that the boundaries are not included in the range.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyBooleanState}
	 * @see #isBetween(long, long)
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isBetweenExclusive(long min, long max);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is between the given values, {@code false} otherwise.
	 * <p>
	 * This method is exclusive, meaning that the boundaries are not included in the range.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyBooleanState}
	 * @see #isBetween(float, float)
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isBetweenExclusive(float min, float max);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is between the given values, {@code false} otherwise.
	 * <p>
	 * This method is exclusive, meaning that the boundaries are not included in the range.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyBooleanState}
	 * @see #isBetween(double, double)
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isBetweenExclusive(double min, double max);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is between the given values, {@code false} otherwise.
	 * <p>
	 * This method is exclusive, meaning that the boundaries are not included in the range.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyBooleanState}
	 */
	ReadOnlyBooleanState isBetweenExclusive(ObservableValue<? extends Number> min, ObservableValue<? extends Number> max);

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is zero, {@code false} otherwise.
	 *
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isZero();

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is not zero, {@code false} otherwise.
	 *
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isNotZero();

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is positive, {@code false} otherwise.
	 *
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isPositive();

	/**
	 * Creates a new {@link ReadOnlyBooleanState} that is {@code true} if the value of this state
	 * is negative, {@code false} otherwise.
	 *
	 * @return the new {@code ReadOnlyBooleanState}
	 * @since 3.0.0
	 */
	ReadOnlyBooleanState isNegative();

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the sum of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to add
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> add(int value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the sum of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to add
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> add(long value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the sum of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to add
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> add(float value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the sum of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to add
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> add(double value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the sum of this state and the value of
	 * the given {@link ObservableValue}.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to add
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> add(ObservableValue<? extends Number> value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the difference of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to subtract
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> subtract(int value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the difference of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to subtract
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> subtract(long value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the difference of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to subtract
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> subtract(float value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the difference of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to subtract
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> subtract(double value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the difference of this state and the value of
	 * the given {@link ObservableValue}.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to subtract
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> subtract(ObservableValue<? extends Number> value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the product of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to multiply
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> multiply(int value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the product of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to multiply
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> multiply(long value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the product of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to multiply
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> multiply(float value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the product of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to multiply
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> multiply(double value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the product of this state and the value of
	 * the given {@link ObservableValue}.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to multiply
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> multiply(ObservableValue<? extends Number> value);

	/**
	 * Creates a new {@link ReadOnlyDoubleState} that holds the quotient of this state and the given value.
	 *
	 * @param value the value to divide by
	 * @return the new {@code ReadOnlyDoubleState}
	 * @since 3.0.0
	 */
	ReadOnlyDoubleState divide(int value);

	/**
	 * Creates a new {@link ReadOnlyDoubleState} that holds the quotient of this state and the given value.
	 *
	 * @param value the value to divide by
	 * @return the new {@code ReadOnlyDoubleState}
	 * @since 3.0.0
	 */
	ReadOnlyDoubleState divide(long value);

	/**
	 * Creates a new {@link ReadOnlyDoubleState} that holds the quotient of this state and the given value.
	 *
	 * @param value the value to divide by
	 * @return the new {@code ReadOnlyDoubleState}
	 * @since 3.0.0
	 */
	ReadOnlyDoubleState divide(float value);

	/**
	 * Creates a new {@link ReadOnlyDoubleState} that holds the quotient of this state and the given value.
	 *
	 * @param value the value to divide by
	 * @return the new {@code ReadOnlyDoubleState}
	 * @since 3.0.0
	 */
	ReadOnlyDoubleState divide(double value);

	/**
	 * Creates a new {@link ReadOnlyDoubleState} that holds the quotient of this state and the value of
	 * the given {@link ObservableValue}.
	 *
	 * @param value the value to divide by
	 * @return the new {@code ReadOnlyDoubleState}
	 * @since 3.0.0
	 */
	ReadOnlyDoubleState divide(ObservableValue<? extends Number> value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the minimum of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> min(int value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the minimum of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> min(long value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the minimum of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> min(float value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the minimum of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> min(double value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the minimum of this state and the value of
	 * the given {@link ObservableValue}.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> min(ObservableValue<? extends Number> value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the maximum of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> max(int value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the maximum of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> max(long value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the maximum of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> max(float value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the maximum of this state and the given value.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> max(double value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the maximum of this state and the value of
	 * the given {@link ObservableValue}.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param value the value to compare to
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> max(ObservableValue<? extends Number> value);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the value of this state clamped to the given range.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> clamp(int min, int max);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the value of this state clamped to the given range.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> clamp(long min, long max);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the value of this state clamped to the given range.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> clamp(float min, float max);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the value of this state clamped to the given range.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> clamp(double min, double max);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the value of this state clamped to the given range.
	 * <p>
	 * The new state will be of the most specific type that can hold both values.
	 *
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<? extends Number> clamp(ObservableValue<? extends Number> min, ObservableValue<? extends Number> max);

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the negation of this state.
	 *
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<T> negate();

	/**
	 * Creates a new {@link ReadOnlyNumberState} that holds the absolute value of this state.
	 *
	 * @return the new {@code ReadOnlyNumberState}
	 * @since 3.0.0
	 */
	ReadOnlyNumberState<T> abs();

	/**
	 * Creates a new {@link ReadOnlyIntegerState} that holds the value of this state rounded
	 * to the nearest integer.
	 *
	 * @return the new {@code ReadOnlyIntegerState}
	 * @since 3.0.0
	 */
	ReadOnlyIntegerState round();

	/**
	 * Creates a new {@link ReadOnlyIntegerState} that holds the value of this state rounded
	 * away from zero to the nearest integer.
	 *
	 * @return the new {@code ReadOnlyIntegerState}
	 * @since 3.0.0
	 */
	ReadOnlyIntegerState roundUp();

	/**
	 * Creates a new {@link ReadOnlyIntegerState} that holds the value of this state rounded
	 * towards zero to the nearest integer.
	 *
	 * @return the new {@code ReadOnlyIntegerState}
	 * @since 3.0.0
	 */
	ReadOnlyIntegerState roundDown();

	/**
	 * Creates a new {@link ReadOnlyIntegerState} that holds the value of this state rounded
	 * towards negative infinity to the nearest integer.
	 *
	 * @return the new {@code ReadOnlyIntegerState}
	 * @since 3.0.0
	 */
	ReadOnlyIntegerState floor();

	/**
	 * Creates a new {@link ReadOnlyIntegerState} that holds the value of this state rounded
	 * towards positive infinity to the nearest integer.
	 *
	 * @return the new {@code ReadOnlyIntegerState}
	 * @since 3.0.0
	 */
	ReadOnlyIntegerState ceiling();

}
